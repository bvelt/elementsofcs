package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

/**
 * IF/THEN conditional gate composed of internal NOT and OR gates
 * 
 * <pre>
 * IF A, THEN B = OR(NOT(A), B)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class ConditionalCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final AOrNotBCompositeGate orGate;

  public ConditionalCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);

    // OR(B, NOT(A))
    orGate = new AOrNotBCompositeGate(inputB, inputA, output);
  }

  @Override
  public void eval() {
    orGate.eval();
  }

  @Override
  public void reset() {
    orGate.reset();
  }

  @Override
  public String toString() {
    return "ConditionalCompositeGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
