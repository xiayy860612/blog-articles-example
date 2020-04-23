from airflow import DAG
from airflow.operators.dummy_operator import DummyOperator
from airflow.operators.python_operator import PythonOperator
from airflow.utils import dates

default_args = {
    'owner': 'airflow-demo',
    'start_date': dates.days_ago(0),
}

dag = DAG(
    dag_id='manual_dag',
    default_args=default_args,
    schedule_interval=None
)

start = DummyOperator(
    dag=dag,
    task_id="start",
    queue="queue_one"
)


def task_process():
    """
    worker dependence modules imported in function scope,
    cannot be global because scheduler environment maybe no required modules
    """
    import os
    os.system('echo "hello world" >> /data/test.txt')


process = PythonOperator(
    task_id='process',
    dag=dag,
    provide_context=False,
    python_callable=task_process,
    queue="queue_two"
)

end = DummyOperator(
    dag=dag,
    task_id="end",
    queue="queue_one"
)

start >> process >> end
