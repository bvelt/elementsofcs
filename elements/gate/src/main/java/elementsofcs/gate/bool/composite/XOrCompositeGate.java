package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BinaryPredicateGate;

/**
 * XOR composite gate composed on internal NOT, AND, and OR gates
 * 
 * <pre>
 * XOR(A, B) = OR(AND(A, NOT(B)), AND(NOT(A), B))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class XOrCompositeGate implements BinaryPredicateGate, CompositeGate {

  private final NotCompositeGate notAGate;
  private final NotCompositeGate notBGate;

  private final AndCompositeGate aAndNotBGate;
  private final AndCompositeGate notAAndBGate;
  private final OrCompositeGate leftOrRightGate;

  public XOrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super();
    // NOT(A)
    Pin notAOut = new Pin("notAOut");
    notAGate = new NotCompositeGate(inputA, notAOut);

    // NOT(B)
    Pin notBOut = new Pin("notBOut");
    notBGate = new NotCompositeGate(inputB, notBOut);

    // AND(A, NOT(B))
    Pin aAndNotBOut = new Pin("aAndNotBOut");
    aAndNotBGate = new AndCompositeGate(inputA, notBOut, aAndNotBOut);

    Pin notAAndBOut = new Pin("notAAndBOut");
    notAAndBGate = new AndCompositeGate(notAOut, inputB, notAAndBOut);

    leftOrRightGate = new OrCompositeGate(aAndNotBOut, notAAndBOut, output);
  }

  @Override
  public void eval() {
    notAGate.eval();
    notBGate.eval();
    aAndNotBGate.eval();
    notAAndBGate.eval();
    leftOrRightGate.eval();
  }

  @Override
  public void reset() {
    notAGate.reset();
    notBGate.reset();
    aAndNotBGate.reset();
    notAAndBGate.reset();
    leftOrRightGate.reset();
  }

  @Override
  public Pin getInputA() {
    return notAGate.getInput();
  }

  @Override
  public Pin getInputB() {
    return notBGate.getInput();
  }

  @Override
  public Pin getOutput() {
    return leftOrRightGate.getOutput();
  }

  @Override
  public String toString() {
    return "XOrCompositeGate [getInputA()=" + getInputA() + ", getInputB()=" + getInputB() + ", getOutput()=" + getOutput() + "]";
  }

}
