This repository is a free Spark implementation of the TPC-DS Query #3 benchmark
(see: https://beam.apache.org/documentation/sdks/java/testing/tpcds/).

For more information on the benchmarks and a comparison with Spark-Dataproc and Google BigQuery, check this blog: https://medium.com/cts-technologies/bigquery-spark-or-dataflow-a-story-of-speed-and-other-comparisons-fb1b8fea3619

### How to launch the benchmark
1. In your Google project, create a BigQuery dataset
2. Load the 3 tables with the already generated data (this command supposes that the dataset just created is called *tpcds_1TB*):
>   bq load --source_format PARQUET tpcds_1TB.store_sales gs://beam-tpcds/datasets/parquet/nonpartitioned/1000GB/store_sales/part*.snappy.parquet
> 
>   bq load --source_format PARQUET tpcds_1TB.date_dim gs://beam-tpcds/datasets/parquet/nonpartitioned/1000GB/date_dim/part*.snappy.parquet
>
>   bq load --source_format PARQUET tpcds_1TB.item gs://beam-tpcds/datasets/parquet/nonpartitioned/1000GB/item/part*.snappy.parquet
3. Edit the _Parameters.java_ and the _launch.sh_ files and fill the TODOs. Make sure that the regions between the BigQuery dataset, the GCS bucket, the region and subnetwork where the Dataproc nodes run are all aligned.
4. Compile the code and create the Jar:
>  mvn package
5. Copy the package to the Cloud bucket:
> gsutil cp target/SparkQuery3-1.0.jar gs://<TODO write here your own bucket, the same as the one in the launch.sh file>/
6. Execute the _launch.sh_ script


### About this Git repository
This code is provided by CTS as an example. It is not meant to be run in any "production environment", and CTS does not aim to maintain it.
For these reasons, we won't accept any Pull Request.
