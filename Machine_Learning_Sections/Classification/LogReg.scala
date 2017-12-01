import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.sql.SparkSession

import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

val spark = SparkSession.builder().getOrCreate()

val data = spark.read.option("header","true").option("inferSchema","true").format("csv").load("titanic.csv")
data.printSchema()

val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example Data Row")
for(ind <- Range(1,colnames.length)){
  println(colnames(ind))
  println(firstrow(ind))
  println("\n")
}

val logreddataall = (data.select(data("Survived").as("label"), $"Pclass", $"Name",
    $"Sex", $"Age", $"SibSp", $"Parch", $"Fare", $"Embarked"))

val logregdata = logregdataall.na.drop()
