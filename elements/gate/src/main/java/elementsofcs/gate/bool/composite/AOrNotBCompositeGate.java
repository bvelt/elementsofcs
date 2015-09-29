package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

public class AOrNotBCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NotCompositeGate notBGate;
  private final OrCompositeGate orGate;

  public AOrNotBCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);

    Pin notBOut = new Pin("notBOut");
    notBGate = new NotCompositeGate(inputB, notBOut);

    orGate = new OrCompositeGate(inputA, notBOut, output);
  }

  @Override
  public void eval() {
    notBGate.eval();
    orGate.eval();
  }

  @Override
  public void reset() {
    notBGate.reset();
    orGate.reset();
  }

  @Override
  public String toString() {
    return "AOrNotBCompositeGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
