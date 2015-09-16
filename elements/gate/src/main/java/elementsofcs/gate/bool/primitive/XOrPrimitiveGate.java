package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

/**
 * Gate that implements boolean XOR function (i.e. exclusive or)
 * 
 * @author brentvelthoen
 *
 */
public class XOrPrimitiveGate extends AbstractBinaryPredicateGate implements PrimitiveGate {

  public XOrPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  @Override
  public void eval() {
    output.setValue(inputA.getValue() != inputB.getValue());
  }

  @Override
  public String toString() {
    return "XOrPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
