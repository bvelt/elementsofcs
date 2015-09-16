package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;
import elementsofcs.gate.bool.primitive.NAndPrimitiveGate;

/**
 * OR composite gate composed of three internal NAND gates.
 * 
 * <pre>
 * OR(X, Y) = NAND(NAND(X, X), NAND(Y, Y))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class OrCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NAndPrimitiveGate nandA;
  private final NAndPrimitiveGate nandB;
  private final NAndPrimitiveGate nandC;

  private final Pin internalA = new Pin("internalA");
  private final Pin internalB = new Pin("internalB");

  public OrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
    nandA = new NAndPrimitiveGate(inputA, inputA, internalA);
    nandB = new NAndPrimitiveGate(inputB, inputB, internalB);
    nandC = new NAndPrimitiveGate(internalA, internalB, output);
  }

  @Override
  public void eval() {
    nandA.eval();
    nandB.eval();
    nandC.eval();
  }

  @Override
  public void reset() {
    nandA.reset();
    nandB.reset();
    nandC.reset();
  }

  @Override
  public String toString() {
    return "OrCompositeGate [nandA=" + nandA + ", nandB=" + nandB + ", nandC=" + nandC + "]";
  }

}
