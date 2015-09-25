package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BooleanGate;

/**
 * Multiplexor composite gate composed of internal gates
 * 
 * <pre>
 * MUX(A, B, Sel) = OR(AND(Sel, A), AND(NOT(Sel), B))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class MuxCompositeGate implements CompositeGate, BooleanGate {

  private final Pin inputA;
  private final Pin inputB;
  private final Pin select;
  private final Pin output;

  private final NotCompositeGate notSelGate;
  private final AndCompositeGate leftSelAndAGate;
  private final AndCompositeGate rightNotSelAndBGate;
  private final OrCompositeGate orLeftAndRightGate;

  public MuxCompositeGate(Pin inputA, Pin inputB, Pin select, Pin output) {
    super();
    this.inputA = inputA;
    this.inputB = inputB;
    this.select = select;
    this.output = output;

    // AND(Sel, A)
    Pin leftSelAndAOut = new Pin("leftSelAndAOut");
    leftSelAndAGate = new AndCompositeGate(select, inputA, leftSelAndAOut);

    // NOT(Sel)
    Pin notSelOut = new Pin("notSelOut");
    notSelGate = new NotCompositeGate(select, notSelOut);

    // AND(NOT(Sel), B)
    Pin rightNotSelAndBOut = new Pin("rightNotSelAndBOut");
    rightNotSelAndBGate = new AndCompositeGate(notSelOut, inputB, rightNotSelAndBOut);

    // OR(AND(Sel, A), AND(NOT(Sel), B))
    orLeftAndRightGate = new OrCompositeGate(leftSelAndAOut, rightNotSelAndBOut, output);
  }

  public Pin getInputA() {
    return inputA;
  }

  public Pin getInputB() {
    return inputB;
  }

  public Pin getSelect() {
    return select;
  }

  public Pin getOutput() {
    return output;
  }

  @Override
  public void eval() {
    notSelGate.eval();
    leftSelAndAGate.eval();
    rightNotSelAndBGate.eval();
    orLeftAndRightGate.eval();
  }

  @Override
  public void reset() {
    notSelGate.reset();
    leftSelAndAGate.reset();
    rightNotSelAndBGate.reset();
    orLeftAndRightGate.reset();
  }

  @Override
  public String toString() {
    return "MuxCompositeGate [notS=" + notSelGate + ", andX=" + leftSelAndAGate + ", andY=" + rightNotSelAndBGate + ", or=" + orLeftAndRightGate + "]";
  }

}
