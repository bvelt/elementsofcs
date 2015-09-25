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

  private final NAndPrimitiveGate notAAndBGate;
  private final NAndPrimitiveGate notLeftAndRightGate;

  public AndCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super();
    // NAND(A, B)
    Pin notAAndBOut = new Pin("notAAndBOut");
    notAAndBGate = new NAndPrimitiveGate(inputA, inputB, notAAndBOut);

    // NAND(NAND(A, B), NAND(A, B))
    notLeftAndRightGate = new NAndPrimitiveGate(notAAndBOut, notAAndBOut, output);
  }

  @Override
  public void eval() {
    notAAndBGate.eval();
    notLeftAndRightGate.eval();
  }

  @Override
  public Pin getInputA() {
    return notAAndBGate.getInputA();
  }

  @Override
  public Pin getInputB() {
    return notAAndBGate.getInputB();
  }

  @Override
  public Pin getOutput() {
    return notLeftAndRightGate.getOutput();
  }

  @Override
  public void reset() {
    notAAndBGate.reset();
    notLeftAndRightGate.reset();
  }

  @Override
  public String toString() {
    return "AndCompositeGate [getInputA()=" + getInputA() + ", getInputB()=" + getInputB() + ", getOutput()=" + getOutput() + "]";
  }

}
