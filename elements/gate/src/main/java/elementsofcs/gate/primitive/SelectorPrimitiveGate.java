package elementsofcs.gate.primitive;

import java.util.Objects;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;

/**
 * Three-input multiplexor gate that outputs value of either input A or B based
 * on selector pin.
 * 
 * @author brentvelthoen
 *
 */
public class SelectorPrimitiveGate implements PrimitiveGate {

  private final Pin inputA;
  private final Pin inputB;
  private final Pin selector;
  private final Pin output;

  public SelectorPrimitiveGate(Pin inputA, Pin inputB, Pin selector, Pin output) {
    super();
    Objects.requireNonNull(inputA, "input");
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
    return "SelectorPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", selector=" + selector + ", output=" + output + "]";
  }

}
