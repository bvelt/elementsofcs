package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BooleanGate;

/**
 * Multiplexor composite gate composed of internal gates
 * 
 * <pre>
 * MUX(A, B, Sel) = OR(AND(A, Sel), AND(B, NOT(Sel))
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

  private final AndCompositeGate leftAndGate;
  private final AAndNotBCompositeGate rightAndGate;
  private final OrCompositeGate leftOrRightGate;

  public MuxCompositeGate(Pin inputA, Pin inputB, Pin select, Pin output) {
    super();
    this.inputA = inputA;
    this.inputB = inputB;
    this.select = select;
    this.output = output;

    // AND(A, Sel)
    Pin leftAndOut = new Pin();
    leftAndGate = new AndCompositeGate(inputA, select, leftAndOut);

    // AND(B, NOT(Sel))
    Pin rightAndOut = new Pin();
    rightAndGate = new AAndNotBCompositeGate(inputB, select, rightAndOut);

    // OR(AND(A, Sel), AND(B, NOT(Sel)))
    leftOrRightGate = new OrCompositeGate(leftAndOut, rightAndOut, output);
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
    leftAndGate.eval();
    rightAndGate.eval();
    leftOrRightGate.eval();
  }

  @Override
  public void reset() {
    leftAndGate.reset();
    rightAndGate.reset();
    leftOrRightGate.reset();
  }

  @Override
  public String toString() {
    return "MuxCompositeGate [inputA=" + inputA + ", inputB=" + inputB + ", select=" + select + ", output=" + output + "]";
  }

}
