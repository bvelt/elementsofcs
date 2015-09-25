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

  private final NotCompositeGate notSelGate;

  private final AndCompositeGate outputAGate;
  private final AndCompositeGate outputBGate;

  public DMuxCompositeGate(Pin input, Pin select, Pin outputA, Pin outputB) {
    super();
    this.input = input;
    this.select = select;
    this.outputA = outputA;
    this.outputB = outputB;

    // AND(Sel, In)=OutputA
    outputAGate = new AndCompositeGate(select, input, outputA);

    // NOT(Sel)
    Pin notSelOut = new Pin("notSelOut");
    notSelGate = new NotCompositeGate(select, notSelOut);

    // AND(NOT(Sel), In)=OutputB
    outputBGate = new AndCompositeGate(notSelOut, input, outputB);
  }

  @Override
  public void eval() {
    outputAGate.eval();
    notSelGate.eval();
    outputBGate.eval();
  }

  public Pin getInput() {
    return input;
  }

  public Pin getOutputA() {
    return outputA;
  }

  public Pin getOutputB() {
    return outputB;
  }

  public Pin getSelect() {
    return select;
  }

  @Override
  public void reset() {
    outputAGate.reset();
    notSelGate.reset();
    outputBGate.reset();
  }

  @Override
  public String toString() {
    return "DMuxCompositeGate [getInput()=" + getInput() + ", getOutputA()=" + getOutputA() + ", getOutputB()=" + getOutputB() + ", getSelect()=" + getSelect()
        + "]";
  }

}
