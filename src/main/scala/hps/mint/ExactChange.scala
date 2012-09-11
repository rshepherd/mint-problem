package hps.mint

class ExactChange(weight: Float) extends Mint(weight) {

  override def compute(denoms: List[Int]) = {
    
    val coinCounts = new Array[Float](100) 
    coinCounts(0) = 0.0F
    for (i <- 1 to 99) {
      var min = Float.PositiveInfinity
      for (denom <- denoms if denom <= i)
        if (coinCounts(i - denom) + 1.0F < min)
          min = coinCounts(i - denom) + 1.0F;
      coinCounts(i) = min
    }
    
    applyWeight(coinCounts).sum
  }

}