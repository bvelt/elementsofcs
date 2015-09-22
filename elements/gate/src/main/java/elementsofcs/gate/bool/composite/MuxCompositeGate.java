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

  private final NotCompositeGate notS;
  private final AndCompositeGate andX;
  private final AndCompositeGate andY;
  private final OrCompositeGate or;

  public MuxCompositeGate(Pin inputA, Pin inputB, Pin select, Pin output) {
    super();
    this.inputA = inputA;
    this.inputB = inputB;
    this.select = select;
    this.output = output;

    Pin outNotS = new Pin("outNotS");
    notS = new NotCompositeGate(select, outNotS);

    Pin outX = new Pin("outX");
    andX = new AndCompositeGate(select, inputA, outX);

    Pin outY = new Pin("outY");
    andY = new AndCompositeGate(outNotS, inputB, outY);

    or = new OrCompositeGate(outX, outY, output);
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
    notS.eval();
    andX.eval();
    andY.eval();
    or.eval();
  }

  @Override
  public void reset() {
    notS.reset();
    andX.reset();
    andY.reset();
    or.reset();
  }

  @Override
  public String toString() {
    return "MuxCompositeGate [notS=" + notS + ", andX=" + andX + ", andY=" + andY + ", or=" + or + "]";
  }

}
