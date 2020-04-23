FROM ubuntu:18.04

ENV LC_ALL=C.UTF-8
ENV LANG=C.UTF-8

ENV AIRFLOW_NAME=airflow
ENV AIRFLOW_HOME=/home/airflow
ENV AIRFLOW_UID=3000
ENV AIRFLOW_GID=3000
ENV PIP_SOURCE=http://pypi.mirrors.ustc.edu.cn/simple/

COPY sources.list.ali /etc/apt/sources.list.ali
COPY requirements.txt /requirements.txt

RUN groupadd -r --gid=$AIRFLOW_GID $AIRFLOW_NAME \
    && useradd -d $AIRFLOW_HOME -s /bin/bash -m -r -u $AIRFLOW_UID -g $AIRFLOW_GID $AIRFLOW_NAME \
    && mv /etc/apt/sources.list.ali /etc/apt/sources.list \
    && apt-get clean && apt-get update && apt-get upgrade -y \
    && apt-get install -y apt-utils \
    && apt-get install -y build-essential python3.6 python3-dev python3-pip \
        libmysqlclient-dev libssl-dev \
    && python3.6 -m pip install -i ${PIP_SOURCE} --trusted-host pypi.mirrors.ustc.edu.cn -U pip \
    && pip install --no-cache-dir -i ${PIP_SOURCE} --trusted-host pypi.mirrors.ustc.edu.cn -r /requirements.txt \
    && apt-get purge -y --auto-remove && apt-get clean \
    && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* /requirements.txt

USER $AIRFLOW_UID
WORKDIR $AIRFLOW_HOME
VOLUME $AIRFLOW_HOME

ENTRYPOINT ["/bin/bash", "-c"]