package hps.mint

object ExactChange extends App with Weighted with Printer {

  val MultiFiveWeight = parseWeight(args)

  def compute(denoms: List[Int]) = {
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

  println("Denom\t" + bestDenoms 
          + "\nExact#\t" + bestNumber 
          + "\nRuntime\t" + (System.currentTimeMillis - start) )
}