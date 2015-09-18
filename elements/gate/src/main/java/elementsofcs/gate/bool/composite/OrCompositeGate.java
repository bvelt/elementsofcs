package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BinaryPredicateGate;
import elementsofcs.gate.bool.primitive.NAndPrimitiveGate;

/**
 * OR composite gate composed of internal NAND gates
 * 
 * <pre>
 * OR(A, B) = NAND(NAND(A, A), NAND(B, B))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class OrCompositeGate implements BinaryPredicateGate, CompositeGate {

  private final NAndPrimitiveGate nandX;
  private final NAndPrimitiveGate nandY;
  private final NAndPrimitiveGate nandZ;

  public OrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    Pin outAnandA = new Pin("outAnandA");
    Pin outBnandB = new Pin("outBnandB");
    nandX = new NAndPrimitiveGate(inputA, inputA, outAnandA);
    nandY = new NAndPrimitiveGate(inputB, inputB, outBnandB);
    nandZ = new NAndPrimitiveGate(outAnandA, outBnandB, output);
  }

  @Override
  public Pin getInputA() {
    return nandX.getInputA();
  }

  @Override
  public Pin getInputB() {
    return nandY.getInputA();
  }

  @Override
  public Pin getOutput() {
    return nandZ.getOutput();
  }

  @Override
  public void eval() {
    nandX.eval();
    nandY.eval();
    nandZ.eval();
  }

  @Override
  public void reset() {
    nandX.reset();
    nandY.reset();
    nandZ.reset();
  }

  @Override
  public String toString() {
    return "OrCompositeGate [nandX=" + nandX + ", nandY=" + nandY + ", nandZ=" + nandZ + "]";
  }

}
