package elementsofcs.gate.bool;

import java.util.Objects;

import elementsofcs.gate.Pin;

public abstract class AbstractUnaryPredicateGate implements UnaryPredicateGate {

  protected final Pin input;
  protected final Pin output;

  public AbstractUnaryPredicateGate(Pin input, Pin output) {
    super();
    Objects.requireNonNull(input, "input");
    Objects.requireNonNull(output, "output");
    this.input = input;
    this.output = output;
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
  public void reset() {
    input.setValue(false);
    output.setValue(false);
  }

}
