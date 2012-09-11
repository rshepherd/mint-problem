package hps.mint

class Exchange(weight: Float) extends Mint(weight) {

  override def compute(denoms: List[Int]) = {
    
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
        val c = ((j - i) % 100 + 100) % 100;
        exchange(i) = Math.min(exchange(i), exactChange(j) + exactChange(c))

      }
    }
    
    applyWeight(exchange).sum
  }

}