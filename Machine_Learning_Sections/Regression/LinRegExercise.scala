////////////////////////////////////////////
//// LINEAR REGRESSION EXERCISE ///////////
/// Complete the commented tasks below ///
/////////////////////////////////////////

// Import LinearRegression
import org.apache.spark.ml.evaluation.RegressionEvaluator
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.ml.tuning.{ParamGridBuilder, TrainValidationSplit}
// Optional: Use the following code below to set the Error reporting
import org.apache.log4j._
Logger.getLogger("org").setLevel(Level.ERROR)

// Start a simple Spark Session
import org.apache.spark.sql.SparkSession
val spark = SparkSession.builder().getOrCreate()
// Use Spark to read in the Ecommerce Customers csv file.
val data = spark.read.option("header","true").option("inferSchema","true").format("csv").load("Clean-Ecommerce.csv")
// Print the Schema of the DataFrame
data.printSchema

// Print out an example Row
val colnames = data.columns
val firstrow = data.head(1)(0)
println("\n")
println("Example Data Row")
for(ind <- Range(1,colnames.length)){
  println(colnames(ind))
  println(firstrow(ind))
  println("\n")
}
// Various ways to do this, just
// choose whichever way you prefer


////////////////////////////////////////////////////
//// Setting Up DataFrame for Machine Learning ////
//////////////////////////////////////////////////

// A few things we need to do before Spark can accept the data!
// It needs to be in the form of two columns
// ("label","features")
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.linalg.Vectors

// Import VectorAssembler and Vectors

// Rename the Yearly Amount Spent Column as "label"
// Also grab only the numerical columns from the data
// Set all of this as a new dataframe called df
val df = data.select(data("Yearly Amount Spent").as("label"), $"Avg Session Length", $"Time on App", $"Time on Website", $"Length of Membership", $"Yearly Amount Spent")

// An assembler converts the input values to a vector
// A vector is what the ML algorithm reads to train a model
val assembler = new VectorAssembler().setInputCols(Array("Avg Session Length", "Time on App", "Time on Website", "Length of Membership", "Yearly Amount Spent")).setOutputCol("features")
// Use VectorAssembler to convert the input columns of df
// to a single output column of an array called "features"
// Set the input columns from which we are supposed to read the values.
// Call this new object assembler

// Use the assembler to transform our DataFrame to the two columns: label and features
val output = assembler.transform(df).select($"label",$"features")

// Create a Linear Regression Model object
val linReg = new LinearRegression()

// Fit the model to the data and call this model lrModel
val lrModel = linReg.fit(output)

// Print the coefficients and intercept for linear regression
println(s"Coefficients: ${lrModel.coefficients}, intercept: ${lrModel.intercept}")
// Summarize the model over the training set and print out some metrics!
// Use the .summary method off your model to create an object
// called trainingSummary
val summary1 = lrModel.summary
println(s"Number of iterations: ${summary1.totalIterations}")
println(s"Objective history: ${summary1.objectiveHistory.toList}")

// Show the residuals, the RMSE, the MSE, and the R^2 Values.
summary1.residuals.show()
println(s"RMSE: ${summary1.rootMeanSquaredError}")
println(s"MSE: ${summary1.meanSquaredError}")
println(s"R2: ${summary1.r2}")

// Great Job!
