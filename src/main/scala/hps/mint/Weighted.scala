package hps.mint

trait Weighted {
  
  def parseWeight(args: Array[String]) = if (args(0) != null) args(0).toFloat else 1.0F
  
  def applyWeight(costs: Array[Float]) = {
    for (i <- 5 until costs.length-1 by 5)
      costs(i) = costs(i) * ExactChange.MultiFiveWeight
    costs
  }
  
}