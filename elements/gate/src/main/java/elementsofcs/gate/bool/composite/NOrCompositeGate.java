package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

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
public class NOrCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final OrCompositeGate aOrBGate;
  private final NotCompositeGate notAOrBGate;

  public NOrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);

    // OR(A, B)
    Pin aOrBOut = new Pin("aOrBOut");
    aOrBGate = new OrCompositeGate(inputA, inputB, aOrBOut);

    // NOT(OR(A, B))
    notAOrBGate = new NotCompositeGate(aOrBOut, output);
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
    return "NOrCompositeGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
