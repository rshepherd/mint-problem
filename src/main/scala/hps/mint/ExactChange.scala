package hps.mint

/*
 * Denom=List(1, 5, 19, 25, 40)
 * Cost=475.0
 * Runtime=41723
 */
object ExactChange extends App with Weighted {

  val MultiFiveWeight = parseWeight(args)

  def compute(denoms: List[Int]) = {
    val costs = new Array[Float](100) 
    costs(0) = 0.0F
    for (i <- 1 to 99) {
      var min = Float.PositiveInfinity
      for (denom <- denoms if denom <= i)
        if (costs(i - denom) + 1.0F < min)
          min = costs(i - denom) + 1.0F;
      costs(i) = min
    }
    applyWeight(costs).sum
  }

  val start = System.currentTimeMillis
  var denom = List[Int]()
  var cost = Float.PositiveInfinity
  (2 to 99).toList.combinations(4)
    .foreach(c => {
      val result = compute(1 :: c)
      if (result < cost) { cost = result; denom = 1 :: c }
    })

  println("Denom=" + denom + "\nCost=" + cost 
      + "\nRuntime=" + (System.currentTimeMillis - start) )
}