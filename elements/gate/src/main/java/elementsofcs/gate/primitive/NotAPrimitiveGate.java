package elementsofcs.gate.primitive;

import elementsofcs.gate.Pin;

public class NotAPrimitiveGate extends AbstractPrimitiveGate {

  public NotAPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  @Override
  public void eval() {
    output.setValue(!inputA.getValue());
  }

  @Override
  public String toString() {
    return "NotAPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
