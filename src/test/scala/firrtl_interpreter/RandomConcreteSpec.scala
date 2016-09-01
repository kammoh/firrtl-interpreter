// See LICENSE for license details.

package firrtl_interpreter

import org.scalatest.{Matchers, FlatSpec}

import scala.collection.mutable

class RandomConcreteSpec extends FlatSpec with Matchers {
  behavior of "random sint generator"

  they should "work over all possible values of given width" in {
    var count = 0

    for(width <- 1 to 8) {
      val (low, high) = TestUtils.extremaOfSIntOfWidth(width)
      val range = new mutable.HashSet[BigInt]

      (low to high).foreach { b => range += b }

      while (count < 10000 && range.nonEmpty) {
        val c = Concrete.randomSInt(width)
        // println(s"got rand sint $c")

        range -= c.value
        count += 1
      }

      assert(range.isEmpty, s"range not empty $range")
    }
  }
}
