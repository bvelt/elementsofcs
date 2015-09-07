package elementsofcs.gate.primitive;

import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;

public abstract class AbstractPrimitiveGate implements Gate {

  protected final Pin inputA;
  protected final Pin inputB;
  protected final Pin output;

  public AbstractPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super();
    Objects.requireNonNull(inputA, "inputA");
    Objects.requireNonNull(inputB, "inputB");
    Objects.requireNonNull(output, "output");
    this.inputA = inputA;
    this.inputB = inputB;
    this.output = output;
  }

  public Pin getInputA() {
    return inputA;
  }

  public Pin getInputB() {
    return inputB;
  }

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
