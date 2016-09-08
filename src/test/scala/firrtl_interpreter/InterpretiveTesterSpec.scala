// See LICENSE for license details.

package firrtl_interpreter

import org.scalatest.{FreeSpec, Matchers}

//scalastyle:off magic.number
class InterpretiveTesterSpec extends FreeSpec with Matchers {
  "The interpretive tester" - {

    "it should mark circuit as stale after poke" in {
      val input =
        """
          |circuit Stop0 :
          |  module Stop0 :
          |    input clk : Clock
          |    input a : UInt<16>
          |    input b : UInt<16>
          |    output c : UInt<16>
          |
          |    reg reg1 : UInt<16>, clk
          |
          |    reg1 <= add(a, b)
          |    c <= add(reg1, UInt(1))
          |
        """.
          stripMargin

      val tester = new InterpretiveTester(input)
      val interpreter = tester.interpreter

      interpreter.circuitState.isStale should be (false)

      tester.poke("a", 1)

      interpreter.circuitState.isStale should be (true)

      tester.peek("c")

      interpreter.circuitState.isStale should be (false)
    }

    "it should allow poking registers" in {
      val input =
        """
          |circuit RegPoker :
          |  module RegPoker :
          |    input clk : Clock
          |    input in : UInt<16>
          |    output out : UInt<16>
          |
          |    reg reg1 : UInt<16>, clk
          |    reg reg2 : UInt<16>, clk
          |    reg reg3 : UInt<16>, clk
          |    reg reg4 : UInt<16>, clk
          |
          |    reg1 <= in
          |    reg2 <= reg1
          |    reg3 <= reg2
          |    reg4 <= reg3
          |    out <= reg4
          |
        """.
          stripMargin

      val tester = new InterpretiveTester(input)
      val interpreter = tester.interpreter

      interpreter.circuitState.isStale should be (false)

      case class State(send: BigInt, expect: BigInt)

      val list = Seq(State(1, 0), State(2, 0), State(3, 0), State(4, 1), State(3, 2), State(2, 3))

      for((state, index) <- list.zipWithIndex) {
        tester.poke("in", state.send)
        tester.step(1)
        tester.peek("out") should be (state.expect)
      }

      def showRegs(): Unit = {
        println((1 to 4).map { n => tester.peek(s"reg$n")}.mkString("registers", ", ", "")) //scalastyle:ignore regex
      }

      for(i <- 1 to 4) {
        tester.poke("in", i)
        tester.step(1)
        showRegs()
      }
      tester.expect("reg3", 2)
      tester.poke("reg3", 7, force = true)
      tester.expect("reg3", 7)

      showRegs()
      tester.step(1)
      tester.expect("reg3", 3)
      tester.expect("reg4", 7)
      showRegs()
    }
  }
}
