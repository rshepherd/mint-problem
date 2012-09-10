package hps.mint

import scala.collection.mutable.HashMap

/*
 * 2-50
 * Denom=List(1, 3, 7, 16, 40, 100)
 * Cost=284.0
 * Runtime=87829
 */
object Exchange extends App with Weighted {

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

    val exchange = new Array[Float](100)
    exchange(0) = 0.0F
    for (i <- 1 to 99) {
      val minGreater = denoms.filter(d => (d > i)).toList(0)
      exchange(i) = exactChange(minGreater - i) + 1
    }
    applyWeight(exchange).sum
  }

  val start = System.currentTimeMillis
  var minDenom = List[Int]()
  var minCost = Float.PositiveInfinity
  (2 to 99).toList.combinations(4)
    .foreach(d => {
      val denom = 1 :: d ::: List(100)
      val cost = compute(denom)
      if (cost < minCost) { minCost = cost; minDenom = denom }
    })

  println("Denom=" + minDenom + "\nCost=" + minCost
    + "\nRuntime=" + (System.currentTimeMillis - start))
}