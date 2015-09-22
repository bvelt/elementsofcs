package elementsofcs.gate.sequential;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.AndCompositeGate;

/**
 * Data Flip-Flop composed of internal AND gates
 * 
 * <pre>
 * out(t) = in(t - 1)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class DFF implements SequentialGate, CompositeGate {

  private final Pin input;
  private final Pin output;

  private final Pin outputNext = new Pin("outputNext");

  private final AndCompositeGate andX;
  private final AndCompositeGate andY;

  public DFF(Pin input, Pin output) {
    super();
    this.input = input;
    this.output = output;

    andX = new AndCompositeGate(outputNext, outputNext, output);
    andY = new AndCompositeGate(input, input, outputNext);
  }

  public Pin getInput() {
    return input;
  }

  public Pin getOutput() {
    return output;
  }

  @Override
  public void eval() {
    andX.eval();
    andY.eval();
  }

  @Override
  public void reset() {
    andX.reset();
    andY.reset();
  }

  @Override
  public String toString() {
    return "DFF [input=" + input + ", outputNext=" + outputNext + ", output=" + output + "]";
  }

}
