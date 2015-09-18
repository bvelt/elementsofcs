package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;
import elementsofcs.gate.bool.AbstractUnaryPredicateGate;

/**
 * Gate that implements boolean NOT function (i.e. negation)
 * 
 * @author brentvelthoen
 *
 */
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
