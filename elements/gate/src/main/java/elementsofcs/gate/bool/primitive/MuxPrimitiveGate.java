package elementsofcs.gate.bool.primitive;

import java.util.Objects;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;
import elementsofcs.gate.bool.BooleanGate;

/**
 * Multiplexor gate that outputs value of either input A or B based on select
 * 
 * @author brentvelthoen
 *
 */
public class MuxPrimitiveGate implements PrimitiveGate, BooleanGate {

  private final Pin inputA;
  private final Pin inputB;
  private final Pin select;
  private final Pin output;

  public MuxPrimitiveGate(Pin inputA, Pin inputB, Pin select, Pin output) {
    super();
    Objects.requireNonNull(inputA, "inputA");
    Objects.requireNonNull(inputB, "inputB");
    Objects.requireNonNull(select, "select");
    Objects.requireNonNull(output, "output");
    this.inputA = inputA;
    this.inputB = inputB;
    this.select = select;
    this.output = output;
  }

  public Pin getInputA() {
    return inputA;
  }

  public Pin getInputB() {
    return inputB;
  }

  public Pin getSelect() {
    return select;
  }

  public Pin getOutput() {
    return output;
  }

  @Override
  public void eval() {
    output.setValue(select.getValue() ? inputA.getValue() : inputB.getValue());
  }

  @Override
  public void reset() {
    inputA.setValue(false);
    inputB.setValue(false);
    select.setValue(false);
    output.setValue(false);
  }

  @Override
  public String toString() {
    return "MuxPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", select=" + select + ", output=" + output + "]";
  }

}
