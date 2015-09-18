package elementsofcs.gate.sequential;

import java.util.List;
import java.util.Objects;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;

public class Counter implements SequentialGate, CompositeGate {

  private final Pin increment;
  private final Pin reset;

  private final Register register;

  public Counter(int size, List<Pin> input,
      Pin load, Pin increment,
      Pin reset, List<Pin> output) {
    super();
    Objects.requireNonNull(increment, "increment");
    this.increment = increment;

    Objects.requireNonNull(load, "load");
    this.reset = reset;

    register = new Register(size, input, load, output);
  }

  public Pin getIncrement() {
    return increment;
  }

  public Pin getReset() {
    return reset;
  }

  public List<Pin> getInput() {
    return register.getInput();
  }

  public List<Pin> getOutput() {
    return register.getOutput();
  }

  public Pin getLoad() {
    return register.getLoad();
  }

  @Override
  public void eval() {
    // TODO Auto-generated method stub

  }

  @Override
  public void reset() {
    register.reset();
    increment.setValue(false);
    reset.setValue(false);
  }

}
