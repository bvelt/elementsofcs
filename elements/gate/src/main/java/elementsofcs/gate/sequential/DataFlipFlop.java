package elementsofcs.gate.sequential;

import java.util.Objects;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;

/**
 * Data flip-flop with a single input and a single output
 * 
 * Specification:
 * 
 * <pre>
 * out(t) = in(t - 1)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class DataFlipFlop implements SequentialGate, PrimitiveGate {

  private final Pin input;
  private final Pin output;
  private final Pin storage = new Pin("storage");

  public DataFlipFlop(Pin input, Pin output) {
    super();
    Objects.requireNonNull(input, "input");
    Objects.requireNonNull(output, "output");
    this.input = input;
    this.output = output;
  }

  @Override
  public void eval() {
    output.setValue(storage.getValue());
    storage.setValue(input.getValue());
  }

  @Override
  public void reset() {
    input.setValue(false);
    output.setValue(false);
    storage.setValue(false);
  }

  public Pin getInput() {
    return input;
  }

  public Pin getOutput() {
    return output;
  }

  @Override
  public String toString() {
    return "DataFlipFlop [input=" + input + ", output=" + output + ", storage=" + storage + "]";
  }

}
