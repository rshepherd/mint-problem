package hps.mint

object Debug {

  def printDenomAndCoinCount(denoms: List[Int], costs: Array[Float]) {
    println("denom=" + denoms)
    var score = 0.0F;
    for (i <- 0 to 99) {
      score += costs(i)
      print(i + " cents=" + costs(i))
      if (i > 0 && i % 10 == 0) { println("") }
      else { print(", ") }
    }
    println("\nscore=" + score)
  }

}