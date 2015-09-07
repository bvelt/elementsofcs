package elementsofcs.gate.primitive;

import elementsofcs.gate.AbstractBinaryPredicateGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;

public class XOrPrimitiveGate extends AbstractBinaryPredicateGate implements PrimitiveGate {

  public XOrPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  @Override
  public void eval() {
    output.setValue(inputA.getValue() != inputB.getValue());
  }

  @Override
  public String toString() {
    return "XOrPrimitiveGate [input=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
