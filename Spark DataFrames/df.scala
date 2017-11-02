import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header","true").option("inferSchema", "true").csv("CitiGroup2006_2008")
df.columns
df.head(5)

for(row <- df.head(5)){
  println(row)
}

df.describe().show()
val df2 = df.withColumn("HighPlusLow", df("High")+df("Low"))
df2.printSchema()
