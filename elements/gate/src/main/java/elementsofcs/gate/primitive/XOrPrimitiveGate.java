package elementsofcs.gate.primitive;

import elementsofcs.gate.Pin;

public class XOrPrimitiveGate extends AbstractPrimitiveGate {

  public XOrPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  @Override
  public void eval() {
    output.setValue(inputA.getValue() != inputB.getValue());
  }

  @Override
  public String toString() {
    return "XOrPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
