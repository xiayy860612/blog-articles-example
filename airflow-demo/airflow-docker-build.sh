#!/bin/bash

docker build --rm --network=host \
-t s2u2m.com/examples/airflow-demo:0.1.0 \
-f airflow.dockerfile .