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

  private final NAndPrimitiveGate notAAndAGate;
  private final NAndPrimitiveGate notBAndBGate;
  private final NAndPrimitiveGate notLeftAndRightGate;

  public OrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super();
    // NAND(A, A)
    Pin notAAndAOut = new Pin("notAAndAOut");
    notAAndAGate = new NAndPrimitiveGate(inputA, inputA, notAAndAOut);

    // NAND(B, B)
    Pin notBAndBOut = new Pin("notBAndBOut");
    notBAndBGate = new NAndPrimitiveGate(inputB, inputB, notBAndBOut);

    // NAND(NAND(A, A), NAND(B, B))
    notLeftAndRightGate = new NAndPrimitiveGate(notAAndAOut, notBAndBOut, output);
  }

  @Override
  public Pin getInputA() {
    return notAAndAGate.getInputA();
  }

  @Override
  public Pin getInputB() {
    return notBAndBGate.getInputA();
  }

  @Override
  public Pin getOutput() {
    return notLeftAndRightGate.getOutput();
  }

  @Override
  public void eval() {
    notAAndAGate.eval();
    notBAndBGate.eval();
    notLeftAndRightGate.eval();
  }

  @Override
  public void reset() {
    notAAndAGate.reset();
    notBAndBGate.reset();
    notLeftAndRightGate.reset();
  }

  @Override
  public String toString() {
    return "OrCompositeGate [getInputA()=" + getInputA() + ", getInputB()=" + getInputB() + ", getOutput()=" + getOutput() + "]";
  }

}
