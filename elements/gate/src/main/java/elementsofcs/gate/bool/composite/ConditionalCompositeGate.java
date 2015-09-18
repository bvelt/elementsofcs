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

  private final OrCompositeGate or;
  private final NotCompositeGate not;

  public ConditionalCompositeGate(Pin inputA, Pin inputB, Pin output) {
    Pin outNotA = new Pin("outNotA");
    not = new NotCompositeGate(inputA, outNotA);
    or = new OrCompositeGate(outNotA, inputB, output);
  }

  @Override
  public void eval() {
    not.eval();
    or.eval();
  }

  @Override
  public void reset() {
    not.reset();
    or.reset();
  }

  @Override
  public Pin getInputA() {
    return not.getInput();
  }

  @Override
  public Pin getInputB() {
    return or.getInputB();
  }

  @Override
  public Pin getOutput() {
    return or.getOutput();
  }

  @Override
  public String toString() {
    return "ConditionalCompositeGate [or=" + or + ", not=" + not + "]";
  }

}
