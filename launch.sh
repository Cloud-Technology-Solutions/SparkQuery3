#!/bin/bash

project="TODO"
region="TODO"
subnet="TODO"
bucket="TODO"

gcloud beta dataproc batches submit spark \
        --batch query3-benchmark-$$ \
        --class co.cts.benchmarks.spark.query3.Main \
        --project ${project} \
        --region ${region} \
        --subnet ${subnet} \
        --version 2.0 \
        --properties=spark.driver.memory=4g,spark.executor.memory=16g,spark.executor.cores=16,spark.executor.instances=2 \
        --jars gs://${bucket}/SparkQuery3-1.0.jar,gs://spark-lib/bigquery/spark-bigquery-with-dependencies_2.13-0.28.0.jar
