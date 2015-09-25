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

  private final OrCompositeGate aOrBGate;
  private final NotCompositeGate notAOrBGate;

  public NOrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super();
    // OR(A, B)
    Pin aOrBOut = new Pin("aOrBOut");
    aOrBGate = new OrCompositeGate(inputA, inputB, aOrBOut);

    // NOT(OR(A, B))
    notAOrBGate = new NotCompositeGate(aOrBOut, output);
  }

  @Override
  public Pin getInputA() {
    return aOrBGate.getInputA();
  }

  @Override
  public Pin getInputB() {
    return aOrBGate.getInputB();
  }

  @Override
  public Pin getOutput() {
    return notAOrBGate.getOutput();
  }

  @Override
  public void eval() {
    aOrBGate.eval();
    notAOrBGate.eval();
  }

  @Override
  public void reset() {
    aOrBGate.reset();
    notAOrBGate.reset();
  }

  @Override
  public String toString() {
    return "NOrCompositeGate [getInputA()=" + getInputA() + ", getInputB()=" + getInputB() + ", getOutput()=" + getOutput() + "]";
  }

}
