import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header","true").option("inferSchema", "true").csv("CitiGroup2006_2008")
// df.columns
// df.head(5)
//
// for(row <- df.head(5)){
//   println(row)
// }
//
// df.describe().show()
// val df2 = df.withColumn("HighPlusLow", df("High")+df("Low"))
// df2.printSchema()

df.printSchema()

import spark.implicits._

df.filter($"Close">480).show()
df.filter("Close > 480").show()

df.filter($"Close" < 480 && $"High" < 480).show()
val CH_low = df.filter($"Close" < 480 && $"High" < 480).collect()
val CH_low_count = df.filter($"Close" < 480 && $"High" < 480).count()

df.filter($"High"===484.40).show()
df.filter("High = 484.40").show()

df.select(corr("High","Low")).show()
