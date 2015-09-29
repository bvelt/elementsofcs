package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

/**
 * XOR composite gate composed on internal NOT, AND, and OR gates
 * 
 * <pre>
 * XOR(A, B) = OR(AND(A, NOT(B)), AND(B, NOT(A)))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class XOrCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final AAndNotBCompositeGate aAndNotBGate;
  private final AAndNotBCompositeGate bAndNotAGate;
  private final OrCompositeGate leftOrRightGate;

  public XOrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);

    // AND(A, NOT(B))
    Pin aAndNotBOut = new Pin("aAndNotBOut");
    aAndNotBGate = new AAndNotBCompositeGate(inputA, inputB, aAndNotBOut);

    // AND(B, NOT(A))
    Pin bAndNotAOut = new Pin("bAndNotAOut");
    bAndNotAGate = new AAndNotBCompositeGate(inputB, inputA, bAndNotAOut);

    leftOrRightGate = new OrCompositeGate(aAndNotBOut, bAndNotAOut, output);
  }

  @Override
  public void eval() {
    aAndNotBGate.eval();
    bAndNotAGate.eval();
    leftOrRightGate.eval();
  }

  @Override
  public void reset() {
    aAndNotBGate.reset();
    bAndNotAGate.reset();
    leftOrRightGate.reset();
  }

  @Override
  public String toString() {
    return "XOrCompositeGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
