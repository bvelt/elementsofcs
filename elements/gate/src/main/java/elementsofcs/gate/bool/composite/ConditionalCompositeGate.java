package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BinaryPredicateGate;

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
public class ConditionalCompositeGate implements BinaryPredicateGate, CompositeGate {

  private final OrCompositeGate notAOrBGate;
  private final NotCompositeGate notAGate;

  public ConditionalCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super();
    // NOT(A)
    Pin notAOut = new Pin("notAOut");
    notAGate = new NotCompositeGate(inputA, notAOut);

    // OR(NOT(A), B)
    notAOrBGate = new OrCompositeGate(notAOut, inputB, output);
  }

  @Override
  public void eval() {
    notAGate.eval();
    notAOrBGate.eval();
  }

  @Override
  public Pin getInputA() {
    return notAGate.getInput();
  }

  @Override
  public Pin getInputB() {
    return notAOrBGate.getInputB();
  }

  @Override
  public Pin getOutput() {
    return notAOrBGate.getOutput();
  }

  @Override
  public void reset() {
    notAGate.reset();
    notAOrBGate.reset();
  }

  @Override
  public String toString() {
    return "ConditionalCompositeGate [getInputA()=" + getInputA() + ", getInputB()=" + getInputB() + ", getOutput()=" + getOutput() + "]";
  }

}
