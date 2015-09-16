package elementsofcs.gate.sequential;

import java.util.Objects;

import elementsofcs.gate.Pin;

public class PrimitiveDFF implements DataFlipFlop {

  private final Pin input;
  private final Pin output;
  private final Pin storage = new Pin("storage");

  public PrimitiveDFF(Pin input, Pin output) {
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

  @Override
  public Pin getInput() {
    return input;
  }

  @Override
  public Pin getOutput() {
    return output;
  }

  @Override
  public String toString() {
    return "PrimitiveDFF [input=" + input + ", output=" + output + ", storage=" + storage + "]";
  }

}
