package elementsofcs.gate.primitive;

import elementsofcs.gate.AbstractUnaryPredicateGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;

public class NotPrimitiveGate extends AbstractUnaryPredicateGate implements PrimitiveGate {

  public NotPrimitiveGate(Pin input, Pin output) {
    super(input, output);
  }

  @Override
  public void eval() {
    output.setValue(!input.getValue());
  }

  @Override
  public String toString() {
    return "NotPrimitiveGate [input=" + input + ", output=" + output + "]";
  }

}