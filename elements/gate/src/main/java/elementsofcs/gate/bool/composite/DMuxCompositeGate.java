package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BooleanGate;

/**
 * De-multiplexor gate composed of internal NOT and AND gates
 * 
 * <pre>
 * DMUX(In,Sel,OutA,OutB) = AND(Sel,In,OutA), AND(NOT(Sel),In,OutB)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class DMuxCompositeGate implements BooleanGate, CompositeGate {

  private final Pin input;
  private final Pin select;
  private final Pin outputA;
  private final Pin outputB;

  private final NotCompositeGate notS;

  private final AndCompositeGate andX;
  private final AndCompositeGate andY;

  public DMuxCompositeGate(Pin input, Pin select, Pin outputA, Pin outputB) {
    super();
    this.input = input;
    this.select = select;
    this.outputA = outputA;
    this.outputB = outputB;

    andX = new AndCompositeGate(select, input, outputA);

    Pin outNotS = new Pin("outNotS");
    notS = new NotCompositeGate(select, outNotS);

    andY = new AndCompositeGate(outNotS, input, outputB);
  }

  public Pin getInput() {
    return input;
  }

  public Pin getSelect() {
    return select;
  }

  public Pin getOutputA() {
    return outputA;
  }

  public Pin getOutputB() {
    return outputB;
  }

  @Override
  public void eval() {
    andX.eval();
    notS.eval();
    andY.eval();
  }

  @Override
  public void reset() {
    andX.reset();
    notS.reset();
    andY.reset();
  }

  @Override
  public String toString() {
    return "DMuxCompositeGate [andX=" + andX + ", notS=" + notS + ", andY=" + andY + "]";
  }

}
