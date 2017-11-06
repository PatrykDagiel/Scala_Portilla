// Missing data
// Start a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()

// Create a DataFrame from Spark Session read csv
// Technically known as class Dataset
val df = spark.read.option("header","true").option("inferSchema","true").csv("ContainsNull.csv")

df.show()

// df.na.drop(2).show()
df.na.fill(100).show()
df.na.fill("Missing Name").show()

df.describe().show()
val df2 = df.na.fill(400.5,Array("Sales"))
df2.na.fill("Missing name",Array("Name")).show()
