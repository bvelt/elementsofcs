package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

/**
 * Gate that implements boolean NOR function (i.e. if a=b=0 then out=T else
 * out=F)
 * 
 * @author brentvelthoen
 *
 */
public class NOrPrimitiveGate extends AbstractBinaryPredicateGate implements PrimitiveGate {

  public NOrPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  @Override
  public void eval() {
    output.setValue(!(inputA.getValue() || inputB.getValue()));
  }

  @Override
  public String toString() {
    return "NOrPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
