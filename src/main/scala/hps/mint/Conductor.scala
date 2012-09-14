package hps.mint

import scala.actors.Actor
import collection.JavaConversions._
import collection.JavaConverters._

object ConductorHarness extends App {
  new Conductor(4.0F) conduct
}

class Denomination(var denom: List[Int])

class Conductor(weight: Float) {

  @volatile var running = true

  val counter = new Counter(this)
  val solver = new Solver(counter, weight)

  def conduct() = {
    counter.start
    
    (2 to 99).toList.combinations(4)
      .filter(d => relativelyPrime(d))
      .foreach(d => { solver ! new Denomination(1 :: d) })

    solver.start 
    
    while (running) {}
    
    System.out.println("Shutting down.")
    System.exit(1)
  }

  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  def relativelyPrime(denoms: List[Int]): Boolean = {
    for (d1 <- denoms)
      for (d2 <- denoms if d2 != d1)
        if (gcd(d1, d2) != 1)
          return false
    counter ! new IncStopAt
    true
  }

}

class Solver(counter: Counter, weight: Float) extends Actor {

  implicit def toJavaList(lst: List[Int]) =
    seqAsJavaList(lst.sortWith(_ > _).map(i => i: java.lang.Integer))
    
  def act {
    loop {
      react {
        case d: Denomination => 
          Mint.judge(Change.compute(d.denom,weight))
          counter ! new IncCount
        case _ => Unit
      }
    }
  }

  override def exceptionHandler = {
    case e: Exception => e.printStackTrace(System.out)
  }

}

class IncCount()

class IncStopAt()

class Counter(conductor: Conductor) extends Actor {

  def act() {   
    var stopAt: Int = 0
    var count: Int = 0
    while (true) {
      receive {
        case c: IncCount =>
          count += 1
          if(count % 50000 == 0) {
            System.out.println("Processing denomination " + count + " of " + stopAt)
          }
          if (count == stopAt) {
            System.out.println("Stopping at " + count + " of " + stopAt)
            Mint.stop
            conductor.running = false
          }
        case s: IncStopAt => stopAt += 1
      }
    }
  }

}