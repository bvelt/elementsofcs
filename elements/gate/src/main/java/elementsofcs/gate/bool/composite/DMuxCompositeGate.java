package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BooleanGate;

/**
 * De-multiplexor gate composed of internal NOT and AND gates
 * 
 * <pre>
 * DMUX(In,Sel,OutA,OutB) = AND(In,Sel,OutA), AND(In,NOT(Sel),OutB)
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
    // NOT(Sel)
    notSelGate = new NotCompositeGate(select);
    // AND(In, Sel)=OutputA
    outputAGate = new AndCompositeGate(input, select, outputA);
    // AND(In, NOT(Sel))=OutputB
    outputBGate = new AndCompositeGate(input, notSelGate.getOutput(), outputB);
  }

  public DMuxCompositeGate(Pin input, Pin select) {
    this(input, select, new Pin(), new Pin());
  }

  @Override
  public void eval() {
    notSelGate.eval();
    outputAGate.eval();
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
    notSelGate.reset();
    outputAGate.reset();
    outputBGate.reset();
  }

  @Override
  public String toString() {
    return "DMuxCompositeGate [input=" + input + ", select=" + select + ", outputA=" + outputA + ", outputB=" + outputB + "]";
  }

}
