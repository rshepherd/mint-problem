package hps.mint

import collection.JavaConversions._
import collection.JavaConverters._

abstract class Mint(weight: Float) {

  // implicitly converts Scala lists to Java lists for solution
  implicit def toJavaList(lst: List[Int]) =
    seqAsJavaList(lst.map(i => i: java.lang.Integer))

  // Method to call to run the test on an ExactChange or Exchange instance
  def run() = {
    val start = System.currentTimeMillis

    var denoms = List[Int]()
    var number = Float.PositiveInfinity
    //(2 to 99).toList.combinations(4)
    List(5, 10, 25, 50).combinations(4)
      .foreach(d => {
        val result = compute(1 :: d ::: List(100))
        if (result < number) { number = result; denoms = d }
      })

    val s = new Solution
    s.denoms = denoms
    s.number = number
    s.elapsed = System.currentTimeMillis - start
    s.weight = weight
    s
  }
  
  // ExactChange and Exchange supply implementations
  def compute(denoms: List[Int]): Float;
  
  def compute(denoms: java.util.List[java.lang.Integer]): Float = {
    compute(denoms.asScala.toList)
  }

  def applyWeight(costs: Array[Float]) = {
    for (i <- 5 until costs.length - 1 by 5)
      costs(i) = costs(i) * weight
    costs
  }

}