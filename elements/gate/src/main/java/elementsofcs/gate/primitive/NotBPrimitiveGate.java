package elementsofcs.gate.primitive;

import elementsofcs.gate.Pin;

public class NotBPrimitiveGate extends AbstractPrimitiveGate {

  public NotBPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  @Override
  public void eval() {
    output.setValue(!inputB.getValue());
  }

  @Override
  public String toString() {
    return "NotBPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
