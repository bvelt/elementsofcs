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

  private final NotCompositeGate notAGate;
  private final OrCompositeGate orGate;

  public ConditionalCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);

    Pin notAOut = new Pin();
    notAGate = new NotCompositeGate(inputA, notAOut);

    orGate = new OrCompositeGate(notAOut, inputB, output);
  }

  @Override
  public void eval() {
    notAGate.eval();
    orGate.eval();
  }

  @Override
  public void reset() {
    notAGate.reset();
    orGate.reset();
  }

  @Override
  public String toString() {
    return "ConditionalCompositeGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
