package elementsofcs.gate.primitive;

import java.util.Objects;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;

/**
 * Multiplexor gate that output value of either inputA A or B based on selector
 * 
 * @author brentvelthoen
 *
 */
public class MultiplexorPrimitiveGate implements PrimitiveGate {

  private final Pin inputA;
  private final Pin inputB;
  private final Pin selector;
  private final Pin output;

  public MultiplexorPrimitiveGate(Pin inputA, Pin inputB, Pin selector, Pin output) {
    super();
    Objects.requireNonNull(inputA, "inputA");
    Objects.requireNonNull(inputB, "inputB");
    Objects.requireNonNull(selector, "selector");
    Objects.requireNonNull(output, "output");
    this.inputA = inputA;
    this.inputB = inputB;
    this.selector = selector;
    this.output = output;
  }

  public Pin getInputA() {
    return inputA;
  }

  public Pin getInputB() {
    return inputB;
  }

  public Pin getSelector() {
    return selector;
  }

  public Pin getOutput() {
    return output;
  }

  @Override
  public void eval() {
    output.setValue(selector.getValue() ? inputA.getValue() : inputB.getValue());
  }

  @Override
  public void reset() {
    inputA.setValue(false);
    inputB.setValue(false);
    selector.setValue(false);
    output.setValue(false);
  }

  @Override
  public String toString() {
    return "MultiplexorPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", selector=" + selector + ", output=" + output + "]";
  }

}
