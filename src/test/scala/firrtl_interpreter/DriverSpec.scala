// See LICENSE for license details.

package firrtl_interpreter

import org.scalatest.{Matchers, FreeSpec}

class DriverSpec extends FreeSpec with Matchers {
  "The Driver class provides a simple caller with run-time parameters" - {
    "topName must be set" in {
      val input =
        """
          |circuit Dummy :
          |  module Dummy :
          |    input x : UInt<1>
          |    output y : UInt<1>
          |    y <= x
        """.stripMargin
      //      val interpreter = Driver.execute(Array.empty[String], input)
      val interpreter = Driver.execute(Array("--fint-verbose"), input)

      interpreter should not be empty

      interpreter.foreach { tester =>
        tester.poke("x", 1)
        tester.expect("y", 1)
      }
    }
  }
}
