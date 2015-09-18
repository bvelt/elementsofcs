package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BinaryPredicateGate;

/**
 * NOR composite gate composed of internal OR and NOT gates
 * 
 * <pre>
 * NOR(A, B) = NOT(OR(A, B))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class NOrCompositeGate implements BinaryPredicateGate, CompositeGate {

  private final OrCompositeGate or;
  private final NotCompositeGate not;

  public NOrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    Pin outAorB = new Pin("outAorB");
    or = new OrCompositeGate(inputA, inputB, outAorB);
    not = new NotCompositeGate(outAorB, output);
  }

  @Override
  public Pin getInputA() {
    return or.getInputA();
  }

  @Override
  public Pin getInputB() {
    return or.getInputB();
  }

  @Override
  public Pin getOutput() {
    return not.getOutput();
  }

  @Override
  public void eval() {
    or.eval();
    not.eval();
  }

  @Override
  public void reset() {
    or.reset();
    not.reset();
  }

  @Override
  public String toString() {
    return "NOrCompositeGate [or=" + or + ", not=" + not + "]";
  }

}
