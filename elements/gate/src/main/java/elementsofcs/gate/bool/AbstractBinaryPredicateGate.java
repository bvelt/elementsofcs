package elementsofcs.gate.bool;

import java.util.Objects;

import elementsofcs.gate.Pin;

public abstract class AbstractBinaryPredicateGate implements BinaryPredicateGate {

  protected final Pin inputA;
  protected final Pin inputB;
  protected final Pin output;

  public AbstractBinaryPredicateGate(Pin inputA, Pin inputB, Pin output) {
    super();
    Objects.requireNonNull(inputA, "inputA");
    Objects.requireNonNull(inputB, "inputB");
    Objects.requireNonNull(output, "output");
    this.inputA = inputA;
    this.inputB = inputB;
    this.output = output;
  }

  @Override
  public Pin getInputA() {
    return inputA;
  }

  @Override
  public Pin getInputB() {
    return inputB;
  }

  @Override
  public Pin getOutput() {
    return output;
  }

  @Override
  public void reset() {
    inputA.setValue(false);
    inputB.setValue(false);
    output.setValue(false);
  }
}
