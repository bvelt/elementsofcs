package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BinaryPredicateGate;
import elementsofcs.gate.bool.primitive.NAndPrimitiveGate;

/**
 * AND composite gate composed of internal NAND gates
 * 
 * <pre>
 * AND(A, B) = NAND(NAND(A, B), NAND(A, B))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class AndCompositeGate implements BinaryPredicateGate, CompositeGate {

  private final NAndPrimitiveGate nandX;
  private final NAndPrimitiveGate nandY;

  public AndCompositeGate(Pin inputA, Pin inputB, Pin output) {
    Pin outAnandB = new Pin("outAnandB");
    nandX = new NAndPrimitiveGate(inputA, inputB, outAnandB);
    nandY = new NAndPrimitiveGate(outAnandB, outAnandB, output);
  }

  @Override
  public void eval() {
    nandX.eval();
    nandY.eval();
  }

  @Override
  public void reset() {
    nandX.reset();
    nandY.reset();
  }

  @Override
  public Pin getInputA() {
    return nandX.getInputA();
  }

  @Override
  public Pin getInputB() {
    return nandX.getInputB();
  }

  @Override
  public Pin getOutput() {
    return nandY.getOutput();
  }

  @Override
  public String toString() {
    return "AndCompositeGate [nandX=" + nandX + ", nandY=" + nandY + "]";
  }

}
