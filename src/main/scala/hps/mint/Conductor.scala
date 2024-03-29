package hps.mint

import scala.actors.Actor
import collection.JavaConversions._
import collection.JavaConverters._

object ConductorHarness extends App {
  new Conductor(4.0F) conduct
}

class Conductor(weight: Float) {

  @volatile var running = true

  val counter = new Counter(this)
  val solver = new Solver(counter, weight)

  def conduct() = {
    counter.start
    
    (2 to 50).toList.combinations(4)
      .foreach(d => { 
         counter ! new IncStopAt  
         solver ! new Denomination(1 :: d)
      })

    System.out.println("Combinations generated.")

    solver.start 
    
    while (running) {}
    
    System.out.println("Shutting down.")
  }

}

class Denomination(var denom: List[Int])

class Solver(counter: Counter, weight: Float) extends Actor {

  implicit def toJavaList(lst: List[Int]) =
    seqAsJavaList(lst.sortWith(_ > _).map(i => i: java.lang.Integer))
    
  def act {
    loop {
      react {
        case d: Denomination => 
          Mint.judge(Change.compute(d.denom,weight))
          counter ! new IncCount
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
          if (count == stopAt) {
            System.out.println(count + " denominations processed.")
            Mint.stop
            conductor.running = false
          }
        case s: IncStopAt => stopAt += 1
      }
    }
  }

}
