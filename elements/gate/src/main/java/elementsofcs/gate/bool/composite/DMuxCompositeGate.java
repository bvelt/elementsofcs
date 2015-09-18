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

  private final NotCompositeGate notS;

  private final AndCompositeGate andX;
  private final AndCompositeGate andY;

  public DMuxCompositeGate(Pin input, Pin selector, Pin outputA, Pin outputB) {
    andX = new AndCompositeGate(selector, input, outputA);

    Pin outNotS = new Pin("outNotS");
    notS = new NotCompositeGate(selector, outNotS);

    andY = new AndCompositeGate(outNotS, input, outputB);
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
