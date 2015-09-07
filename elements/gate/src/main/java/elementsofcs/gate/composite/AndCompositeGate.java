package elementsofcs.gate.composite;

import elementsofcs.gate.AbstractBinaryPredicateGate;
import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.primitive.NAndPrimitiveGate;

/**
 * AND composite gate composed of three intermediate NAND gates.
 * 
 * AND(X,Y) = NAND(NAND(X,Y),NAND(X,Y))
 * 
 * @author brentvelthoen
 *
 */
public class AndCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NAndPrimitiveGate nandA;
  private final NAndPrimitiveGate nandB;
  private final NAndPrimitiveGate nandC;

  private final Pin intermediateA = new Pin("intermediateA");
  private final Pin intermediateB = new Pin("intermediateB");

  public AndCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
    nandA = new NAndPrimitiveGate(inputA, inputB, intermediateA);
    nandB = new NAndPrimitiveGate(inputA, inputB, intermediateB);
    nandC = new NAndPrimitiveGate(intermediateA, intermediateB, output);
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
    return "AndCompositeGate [nandA=" + nandA + ", nandB=" + nandB + ", nandC=" + nandC + ", intermediateA=" + intermediateA + ", intermediateB="
        + intermediateB + "]";
  }

}