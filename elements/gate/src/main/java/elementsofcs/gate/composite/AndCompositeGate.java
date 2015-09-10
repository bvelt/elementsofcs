package elementsofcs.gate.composite;

import elementsofcs.gate.AbstractBinaryPredicateGate;
import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.primitive.NAndPrimitiveGate;

/**
 * AND composite gate composed of three internal NAND gates.
 * 
 * <pre>
 * AND(X, Y) = NAND(NAND(X, Y), NAND(X, Y))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class AndCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NAndPrimitiveGate nandA;
  private final NAndPrimitiveGate nandB;
  private final NAndPrimitiveGate nandC;

  private final Pin internalA = new Pin("internalA");
  private final Pin internalB = new Pin("internalB");

  public AndCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
    nandA = new NAndPrimitiveGate(inputA, inputB, internalA);
    nandB = new NAndPrimitiveGate(inputA, inputB, internalB);
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
    return "AndCompositeGate [nandA=" + nandA + ", nandB=" + nandB + ", nandC=" + nandC + "]";
  }

}
