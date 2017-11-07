// Start a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// Create a DataFrame from Spark Session read csv
// Technically known as class Dataset
val df = spark.read.option("header","true").option("inferSchema","true").csv("CitiGroup2006_2008")

// Show Schema
df.printSchema()
df.select(year(df("Date"))).show()

val df2 = df.withColumn("Year", year(df("Date")))

val dfavgs = df2.groupBy("Year").mean()

dfavgs.select($"Year", $"avg(Close)").orderBy("Year").show()

val df3 = df.withColumn("Year", year(df("Date")))

val dfavgs = df3.groupBy("Year").min()

dfavgs.select($"Year", $"min(Close)").orderBy("Year").show()
