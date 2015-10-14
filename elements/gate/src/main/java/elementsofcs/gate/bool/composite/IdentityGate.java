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
public class IdentityGate extends AbstractUnaryPredicateGate implements CompositeGate {

  private final OrGate orGate;

  public IdentityGate(Pin input, Pin output) {
    super(input, output);
    orGate = new OrGate(input, new Pin(), output);
  }

  public IdentityGate(Pin input) {
    this(input, new Pin());
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
    return "IdentityGate [input=" + input + ", output=" + output + "]";
  }

}
