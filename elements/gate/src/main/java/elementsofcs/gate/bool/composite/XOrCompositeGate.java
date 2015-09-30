package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;
import elementsofcs.gate.bool.primitive.NAndPrimitiveGate;

/**
 * XOR composite gate composed on internal gates
 * 
 * <pre>
 * XOR(A, B) = AND(NAND(A, B), OR(A, B))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class XOrCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NAndPrimitiveGate nandABGate;
  private final OrCompositeGate orABGate;
  private final AndCompositeGate andLeftRightGate;

  public XOrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);

    Pin nandABOut = new Pin("nandABOut");
    nandABGate = new NAndPrimitiveGate(inputA, inputB, nandABOut);

    Pin orABOut = new Pin("orABOut");
    orABGate = new OrCompositeGate(inputA, inputB, orABOut);

    andLeftRightGate = new AndCompositeGate(nandABOut, orABOut, output);
  }

  @Override
  public void eval() {
    nandABGate.eval();
    orABGate.eval();
    andLeftRightGate.eval();
  }

  @Override
  public void reset() {
    nandABGate.reset();
    orABGate.reset();
    andLeftRightGate.reset();
  }

  @Override
  public String toString() {
    return "XOrCompositeGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
