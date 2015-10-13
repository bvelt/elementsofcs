package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

public class AAndNotBCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NotCompositeGate notBGate;
  private final AndCompositeGate andGate;

  public AAndNotBCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);

    Pin notBOut = new Pin();
    notBGate = new NotCompositeGate(inputB, notBOut);

    andGate = new AndCompositeGate(inputA, notBOut, output);
  }

  @Override
  public void eval() {
    notBGate.eval();
    andGate.eval();
  }

  @Override
  public void reset() {
    notBGate.reset();
    andGate.reset();
  }

  @Override
  public String toString() {
    return "AAndNotBCompositeGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
