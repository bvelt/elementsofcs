package elementsofcs.gate.primitive;

import elementsofcs.gate.Pin;

public class AndPrimitiveGate extends AbstractPrimitiveGate {

  public AndPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  @Override
  public void eval() {
    output.setValue(inputA.getValue() && inputB.getValue());
  }

  @Override
  public String toString() {
    return "AndPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
