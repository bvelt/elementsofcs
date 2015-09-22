package elementsofcs.gate.bool.primitive;

import java.util.Objects;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;
import elementsofcs.gate.bool.BooleanGate;

/**
 * Demultiplexor gate that sets either output A or B to input based on select
 * 
 * @author brentvelthoen
 *
 */
public class DMuxPrimitiveGate implements PrimitiveGate, BooleanGate {

  private final Pin input;
  private final Pin select;
  private final Pin outputA;
  private final Pin outputB;

  public DMuxPrimitiveGate(Pin input, Pin select, Pin outputA, Pin outputB) {
    super();
    Objects.requireNonNull(input, "input");
    Objects.requireNonNull(select, "select");
    Objects.requireNonNull(outputA, "outputA");
    Objects.requireNonNull(outputB, "outputB");
    this.input = input;
    this.select = select;
    this.outputA = outputA;
    this.outputB = outputB;
  }

  public Pin getInput() {
    return input;
  }

  public Pin getSelect() {
    return select;
  }

  public Pin getOutputA() {
    return outputA;
  }

  public Pin getOutputB() {
    return outputB;
  }

  @Override
  public void eval() {
    if (select.getValue()) {
      outputA.setValue(input.getValue());
      outputB.setValue(false);
    } else {
      outputB.setValue(input.getValue());
      outputA.setValue(false);
    }
  }

  @Override
  public void reset() {
    input.setValue(false);
    select.setValue(false);
    outputA.setValue(false);
    outputB.setValue(false);
  }

  @Override
  public String toString() {
    return "DMuxPrimitiveGate [input=" + input + ", select=" + select + ", outputA=" + outputA + ", outputB=" + outputB + "]";
  }

}
