import datetime
import logging
import os
import sys
import time

from sqlalchemy import create_engine, text

airflow_db_name = 'airflow-demo'
max_check_min = 3


def check_mysql_setup(mysql_engine):
    try:
        logging.info("check_mysql_setup at %s" % datetime.datetime.now())
        with mysql_engine.connect():
            return True
    except Exception:
        return False


def check_airflow_db_created(mysql_engine):
    with mysql_engine.connect() as con:
        current = datetime.datetime.utcnow().timestamp()
        endtime = current + max_check_min * 60 * 1000
        is_created = False
        sql = text("select 1 from SCHEMATA where SCHEMA_NAME=:schema")
        while not is_created and current < endtime:
            logging.info("check_airflow_db_created at %s" % datetime.datetime.now())
            is_created = True if con.execute(sql, schema=airflow_db_name).scalar() else False
            if is_created:
                break
            time.sleep(10)
            current = datetime.datetime.utcnow().timestamp()
        return is_created


engine = create_engine('mysql://root:airflow@mysql:3306/information_schema')
while not check_mysql_setup(engine):
    time.sleep(10)

airflow_db_created = check_airflow_db_created(engine)
if not airflow_db_created:
    logging.error('db[%s] not created' % airflow_db_name)
    sys.exit(-1)
else:
    rst = os.system('airflow initdb')
    sys.exit(rst)
