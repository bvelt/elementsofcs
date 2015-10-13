package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;
import elementsofcs.gate.bool.primitive.NAndPrimitiveGate;

/**
 * OR composite gate composed of internal gates
 * 
 * <pre>
 * OR(A, B) = NAND(NOT(A), NOT(B))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class OrCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NotCompositeGate notAGate;
  private final NotCompositeGate notBGate;
  private final NAndPrimitiveGate nandGate;

  public OrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
    // NOT(A)
    notAGate = new NotCompositeGate(inputA);
    // NOT(B)
    notBGate = new NotCompositeGate(inputB);
    // NAND(NOT(A), NOT(B))
    nandGate = new NAndPrimitiveGate(notAGate.getOutput(), notBGate.getOutput(), output);
  }

  public OrCompositeGate(Pin inputA, Pin inputB) {
    this(inputA, inputB, new Pin());
  }

  @Override
  public void eval() {
    notAGate.eval();
    notBGate.eval();
    nandGate.eval();
  }

  @Override
  public void reset() {
    notAGate.reset();
    notBGate.reset();
    nandGate.reset();
  }

  @Override
  public String toString() {
    return "OrCompositeGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
