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
public class MuxGate implements CompositeGate, BooleanGate {

  private final Pin inputA;
  private final Pin inputB;
  private final Pin select;
  private final Pin output;

  private final AndGate leftAndGate;

  private final NotGate notSelGate;
  private final AndGate rightAndGate;

  private final OrGate leftOrRightGate;

  public MuxGate(Pin inputA, Pin inputB, Pin select, Pin output) {
    super();
    this.inputA = inputA;
    this.inputB = inputB;
    this.select = select;
    this.output = output;
    // AND(A, Sel)
    leftAndGate = new AndGate(inputA, select);
    // NOT(Sel)
    notSelGate = new NotGate(select);
    // AND(B, NOT(Sel))
    rightAndGate = new AndGate(inputB, notSelGate.getOutput());
    // OR(AND(A, Sel), AND(B, NOT(Sel)))
    leftOrRightGate = new OrGate(leftAndGate.getOutput(), rightAndGate.getOutput(), output);
  }

  public MuxGate(Pin inputA, Pin inputB, Pin select) {
    this(inputA, inputB, select, new Pin());
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
    notSelGate.eval();
    rightAndGate.eval();
    leftOrRightGate.eval();
  }

  @Override
  public void reset() {
    leftAndGate.reset();
    notSelGate.reset();
    rightAndGate.reset();
    leftOrRightGate.reset();
  }

  @Override
  public String toString() {
    return "MuxGate [inputA=" + inputA + ", inputB=" + inputB + ", select=" + select + ", output=" + output + "]";
  }

}
