package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;
import elementsofcs.gate.bool.primitive.NAndPrimitiveGate;

/**
 * AND composite gate composed of internal NOT and NAND gates
 * 
 * <pre>
 * AND(A, B) = NOT(NAND(A, B))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class AndCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NAndPrimitiveGate nandGate;
  private final NotCompositeGate notGate;

  public AndCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);

    // NAND(A, B)
    Pin nandOut = new Pin("nandOut");
    nandGate = new NAndPrimitiveGate(inputA, inputB, nandOut);

    // NOT(NAND(A, B))
    notGate = new NotCompositeGate(nandOut, output);
  }

  @Override
  public void eval() {
    nandGate.eval();
    notGate.eval();
  }

  @Override
  public void reset() {
    nandGate.reset();
    notGate.reset();
  }

  @Override
  public String toString() {
    return "AndCompositeGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
