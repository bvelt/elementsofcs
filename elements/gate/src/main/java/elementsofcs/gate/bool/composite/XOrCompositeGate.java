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

  private final NotCompositeGate notA;
  private final NotCompositeGate notB;

  private final AndCompositeGate andX;
  private final AndCompositeGate andY;
  private final OrCompositeGate or;

  public XOrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    Pin outNotA = new Pin("outNotA");
    notA = new NotCompositeGate(inputA, outNotA);

    Pin outNotB = new Pin("outNotB");
    notB = new NotCompositeGate(inputB, outNotB);

    Pin outX = new Pin("outX");
    andX = new AndCompositeGate(inputA, outNotB, outX);

    Pin outY = new Pin("outY");
    andY = new AndCompositeGate(outNotA, inputB, outY);

    or = new OrCompositeGate(outX, outY, output);
  }

  @Override
  public void eval() {
    notA.eval();
    notB.eval();
    andX.eval();
    andY.eval();
    or.eval();
  }

  @Override
  public void reset() {
    notA.reset();
    notB.reset();
    andX.reset();
    andY.reset();
    or.reset();
  }

  @Override
  public Pin getInputA() {
    return notA.getInput();
  }

  @Override
  public Pin getInputB() {
    return notB.getInput();
  }

  @Override
  public Pin getOutput() {
    return or.getOutput();
  }

  @Override
  public String toString() {
    return "XOrCompositeGate [notA=" + notA + ", notB=" + notB + ", andX=" + andX + ", andY=" + andY + ", or=" + or + "]";
  }

}
