// See LICENSE for license details.

package firrtl_interpreter

import org.scalatest.{Matchers, FlatSpec}

class SourceInfoSpec extends FlatSpec with Matchers {
  behavior of "source information"

  it should "be visible when logging and errors occur" in {
    val stream = getClass.getResourceAsStream("/FullAdder.ir")
    val input = io.Source.fromInputStream(stream).mkString

    val f = FirrtlTerp(input)

    f.evaluator.setVerbose(true)
    f.cycle()
    f.dependencyGraph.sourceInfo("a_and_b") should fullyMatch regex ".*FullAdder.scala 19:22.*"
  }

}
