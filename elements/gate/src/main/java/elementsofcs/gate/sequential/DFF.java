package elementsofcs.gate.sequential;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.IdentityCompositeGate;

/**
 * Data Flip-Flop composed of internal gates
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

  private final Pin buffer = new Pin("buffer");

  private final IdentityCompositeGate writeOutput;
  private final IdentityCompositeGate readInput;

  public DFF(Pin input, Pin output) {
    super();
    this.input = input;
    this.output = output;

    writeOutput = new IdentityCompositeGate(buffer, output);
    readInput = new IdentityCompositeGate(input, buffer);
  }

  public Pin getInput() {
    return input;
  }

  public Pin getOutput() {
    return output;
  }

  @Override
  public void eval() {
    writeOutput.eval();
    readInput.eval();
  }

  @Override
  public void reset() {
    writeOutput.reset();
    readInput.reset();
  }

  @Override
  public String toString() {
    return "DFF [input=" + input + ", buffer=" + buffer + ", output=" + output + "]";
  }

}
