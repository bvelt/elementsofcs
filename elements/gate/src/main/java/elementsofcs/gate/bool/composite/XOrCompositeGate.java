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
    // NAND(A, B)
    nandABGate = new NAndPrimitiveGate(inputA, inputB);
    // OR(A, B)
    orABGate = new OrCompositeGate(inputA, inputB);
    // AND(NAND(A, B), OR(A, B))
    andLeftRightGate = new AndCompositeGate(nandABGate.getOutput(), orABGate.getOutput(), output);
  }

  public XOrCompositeGate(Pin inputA, Pin inputB) {
    this(inputA, inputB, new Pin());
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
