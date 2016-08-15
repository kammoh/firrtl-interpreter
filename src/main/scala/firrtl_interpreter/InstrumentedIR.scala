// See LICENSE for license details.

package firrtl_interpreter

import firrtl.ir.{Mux, Type, Expression}

trait InstrumentedIR

class InstrumentedMux(
                            cond: Expression,
                            tval: Expression,
                            fval: Expression,
                            tpe: Type
                          )
extends Mux(cond, tval, fval, tpe) with InstrumentedIR {
  var trueBranchTaken:  Long = 0
  var falseBranchTaken: Long = 0
  var sourceInfo: String = ""
}
