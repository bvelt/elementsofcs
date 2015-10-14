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
public class ConditionalGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NotGate notAGate;
  private final OrGate orGate;

  public ConditionalGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
    // NOT(A)
    notAGate = new NotGate(inputA);
    // OR(NOT(A), B)
    orGate = new OrGate(notAGate.getOutput(), inputB, output);
  }

  public ConditionalGate(Pin inputA, Pin inputB) {
    this(inputA, inputB, new Pin());
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
    return "ConditionalGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
