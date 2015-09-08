package elementsofcs.gate.primitive;

import java.util.Objects;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;

/**
 * Two-input demultiplexor gate that sets either output A or B to input based on
 * selector pin.
 * 
 * @author brentvelthoen
 *
 */
public class DemultiplexorPrimitiveGate implements PrimitiveGate {

  private final Pin input;
  private final Pin selector;
  private final Pin outputA;
  private final Pin outputB;

  public DemultiplexorPrimitiveGate(Pin input, Pin selector, Pin outputA, Pin outputB) {
    super();
    Objects.requireNonNull(input, "input");
    Objects.requireNonNull(selector, "selector");
    Objects.requireNonNull(outputA, "outputA");
    Objects.requireNonNull(outputB, "outputB");
    this.input = input;
    this.selector = selector;
    this.outputA = outputA;
    this.outputB = outputB;
  }

  @Override
  public void eval() {
    if (selector.getValue()) {
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
    selector.setValue(false);
    outputA.setValue(false);
    outputB.setValue(false);
  }

  @Override
  public String toString() {
    return "DemultiplexorPrimitiveGate [input=" + input + ", selector=" + selector + ", outputA=" + outputA + ", outputB=" + outputB + "]";
  }

}
