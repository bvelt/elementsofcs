package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractUnaryPredicateGate;

/**
 * Gate that maintains identity of input
 * 
 * <pre>
 * IDENTITY(A) = OR(A, False)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class IdentityCompositeGate extends AbstractUnaryPredicateGate implements CompositeGate {

  private final OrCompositeGate orGate;

  public IdentityCompositeGate(Pin input, Pin output) {
    super(input, output);
    orGate = new OrCompositeGate(input, new Pin("alwaysFalse"), output);
  }

  @Override
  public void eval() {
    orGate.eval();
  }

  @Override
  public void reset() {
    orGate.reset();
  }

  @Override
  public String toString() {
    return "IdentityCompositeGate [input=" + input + ", output=" + output + "]";
  }

}
