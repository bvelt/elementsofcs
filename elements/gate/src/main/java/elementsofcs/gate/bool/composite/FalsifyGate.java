package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractUnaryPredicateGate;

/**
 * Gate that outputs False regardless of input
 * 
 * <pre>
 * FALSIFY(A) = AND(A, False)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class FalsifyGate extends AbstractUnaryPredicateGate implements CompositeGate {

  private final AndGate andGate;

  public FalsifyGate(Pin input, Pin output) {
    super(input, output);
    andGate = new AndGate(input, new Pin(), output);
  }

  public FalsifyGate(Pin input) {
    this(input, new Pin());
  }

  @Override
  public void eval() {
    andGate.eval();
  }

  @Override
  public void reset() {
    andGate.reset();
  }

  @Override
  public String toString() {
    return "FalsifyGate [input=" + input + ", output=" + output + "]";
  }

}
