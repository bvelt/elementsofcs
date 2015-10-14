package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;
import elementsofcs.gate.bool.primitive.NAndGate;

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
public class XOrGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NAndGate nandABGate;
  private final OrGate orABGate;
  private final AndGate andLeftRightGate;

  public XOrGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
    // NAND(A, B)
    nandABGate = new NAndGate(inputA, inputB);
    // OR(A, B)
    orABGate = new OrGate(inputA, inputB);
    // AND(NAND(A, B), OR(A, B))
    andLeftRightGate = new AndGate(nandABGate.getOutput(), orABGate.getOutput(), output);
  }

  public XOrGate(Pin inputA, Pin inputB) {
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
    return "XOrGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
