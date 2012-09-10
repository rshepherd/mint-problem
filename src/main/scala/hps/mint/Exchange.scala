package hps.mint

import scala.collection.mutable.HashMap

object Exchange extends App with Weighted with Printer {

  def compute(denoms: List[Int]) = {
    val exactChange = new Array[Float](100)
    exactChange(0) = 0.0F
    for (i <- 1 to 99) {
      var min = Float.PositiveInfinity
      for (denom <- denoms if denom <= i)
        if (exactChange(i - denom) + 1.0F < min)
          min = exactChange(i - denom) + 1.0F;
      exactChange(i) = min
    }

    val exchange = exactChange.clone
    for (i <- 0 until 100) {
      for (j <- 0 until 100) {
        val c = ((j-i) % 100 + 100) % 100;
        exchange(i) = Math.min(exchange(i), exactChange(j) + exactChange(c))
        
      }
    }
    applyWeight(exchange).sum
  }

  val start = System.currentTimeMillis
  var bestDenoms = List[Int]()
  var bestNumber = Float.PositiveInfinity
  //(2 to 99).toList.combinations(4)
  List(5,10,25,50).combinations(4)
    .foreach(d => {
      val denoms = 1 :: d ::: List(100)
      val result = compute(denoms)
      if (result < bestNumber) { bestNumber = result; bestDenoms = denoms }
    })

  println("Denom\t\t" + bestDenoms 
          + "\nExchange#\t" + bestNumber 
          + "\nRuntime\t\t" + (System.currentTimeMillis - start) )
}