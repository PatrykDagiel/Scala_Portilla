def isEven(num:Int): Boolean = {
  return num%2 == 0
}
println(isEven(4))
println(isEven(3))

// 2nd:
def checkList(numbers:List[Int]): Boolean = {
  for(n <- numbers){
    if(n%2==0){
      return true
    }
  }
  return false
}

val evensample = List(1,2,3,4,5)
val oddsample = List(1,3,5,7)

println(checkList(evensample))
println(checkList(oddsample))

//3rd:
def luckySum(numbers:List[Int]): Int = {
  var sum = 0
  for(n<-numbers){
    if(n==7){
      sum += 14
    }
    else sum += n
  }
  return sum
}

val summary = List(1,2,3,4,5,6,7)
println(luckySum(summary))

//4th:
def isBalanced(numbers:List[Int]): Boolean = {
  var sum1 = 0
  var sum2 = 0
  sum2 = numbers.sum
  for(i <- Range(0, numbers.length)){
    sum1 = sum1 + numbers(i)
    sum2 = sum2 - numbers(i)
    if(sum1 == sum2){
      return true
    }
  }
  return false
}

val lista = List(1,2,2,2,2,19)
println(isBalanced(lista))

//5th:
def palindrom(wyraz:String): Boolean = {
  return(wyraz == wyraz.reverse)
}

println(palindrom("makaakam"))
