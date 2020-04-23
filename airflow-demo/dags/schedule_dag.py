from datetime import timedelta, datetime

from airflow import DAG
from airflow.operators.dummy_operator import DummyOperator
from airflow.operators.python_operator import PythonOperator

default_args = {
    'owner': 'airflow-demo',
    'start_date': datetime(2016, 1, 1),
}

dag = DAG(
    dag_id='schedule_dag',
    default_args=default_args,
    schedule_interval=timedelta(days=1)
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
