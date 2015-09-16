package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

/**
 * Gate that implements boolean AND function
 * 
 * @author brentvelthoen
 *
 */
public class AndPrimitiveGate extends AbstractBinaryPredicateGate implements PrimitiveGate {

  public AndPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  @Override
  public void eval() {
    output.setValue(inputA.getValue() && inputB.getValue());
  }

  @Override
  public String toString() {
    return "AndPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
