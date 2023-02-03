package co.cts.benchmarks.spark.query3;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        SparkSession session = getSparkSession();

        readInputTables(session);
        Dataset<Row> rows = query(session);
        saveToBigQuery(rows);
    }

    private static SparkSession getSparkSession() {
        return SparkSession
                .builder()
                .appName("query3-benchmark")
                .getOrCreate();
    }

    private static void readInputTables(SparkSession session) {
        Stream.of(
                Parameters.STORE_SALES_TABLE,
                Parameters.DATE_DIM_TABLE,
                Parameters.ITEM_TABLE
                )
                .forEach(table -> readInputTable(session, table));
    }

    private static void readInputTable(SparkSession session, String fullTableName){
        Dataset<Row>  dataset = session
                .read()
                .format(Parameters.READ_FORMAT)
                .load(fullTableName);

        String table = fullTableName.split("\\.")[2];

        dataset.createOrReplaceTempView(table);
    }

    private static Dataset<Row> query(SparkSession session) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        StringBuilder sb = new StringBuilder();

        try (InputStream inputStream = classloader.getResourceAsStream("query3.sql")) {
            for (int ch; (ch = inputStream.read()) != -1; ) {
                sb.append((char) ch);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String query3 = sb.toString();

        return session.sql(query3);
    }

    public static void saveToBigQuery(Dataset<Row> rows) {
        rows
                .write()
                .option("table", Parameters.RESULT_TABLE)
                .format(Parameters.READ_FORMAT)
                .option("writeMethod", "direct")
                .mode(SaveMode.Overwrite)
                .save();
    }
}
