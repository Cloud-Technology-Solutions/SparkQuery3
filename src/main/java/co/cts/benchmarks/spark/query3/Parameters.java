package co.cts.benchmarks.spark.query3;

public final class Parameters {
    private Parameters(){}

    public static final String DATASET = "TODO";
    public static final String STORE_SALES_TABLE = DATASET + ".store_sales";
    public static final String DATE_DIM_TABLE = DATASET + ".date_dim";
    public static final String ITEM_TABLE = DATASET + ".item";
    public static final String RESULT_TABLE = DATASET + ".output_spark";

    public static final String READ_FORMAT = "com.google.cloud.spark.bigquery";
}
