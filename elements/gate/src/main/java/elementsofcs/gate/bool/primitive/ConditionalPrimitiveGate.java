package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

/**
 * Gate that implements boolean IF/THEN conditional (i.e. implication)
 * 
 * @author brentvelthoen
 *
 */
public class ConditionalPrimitiveGate extends AbstractBinaryPredicateGate implements PrimitiveGate {

  public ConditionalPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  @Override
  public void eval() {
    output.setValue(!inputA.getValue() || inputB.getValue());
  }

  @Override
  public String toString() {
    return "ConditionalPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}