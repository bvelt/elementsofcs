package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

/**
 * Gate that implements boolean OR function
 * 
 * @author brentvelthoen
 *
 */
public class OrPrimitiveGate extends AbstractBinaryPredicateGate implements PrimitiveGate {

  public OrPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  @Override
  public void eval() {
    output.setValue(inputA.getValue() || inputB.getValue());
  }

  @Override
  public String toString() {
    return "OrPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
