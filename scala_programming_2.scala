for (item <- List(1,2,3)) {
  println(item)
}

for(num <- Array.range(0,5)){
  println(num)
}

for(num2 <- Set(1,2,3)){
  println(num2)
}

for(num3 <- Range(0,10)){
  if(num3%2 == 0){
    println(s"$num3 is even")
  }else {
    println(s"$num3 is odd")
  }
}

val name = List("john", "abe", "Cindy", "Cat")

for(name <- names) {
  if(name.startsWith("C")){
    println(s"$name starts with a C")
  }
}
