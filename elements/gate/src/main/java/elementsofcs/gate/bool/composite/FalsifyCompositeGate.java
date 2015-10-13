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
public class FalsifyCompositeGate extends AbstractUnaryPredicateGate implements CompositeGate {

  private final AndCompositeGate andGate;

  public FalsifyCompositeGate(Pin input, Pin output) {
    super(input, output);
    andGate = new AndCompositeGate(input, new Pin(), output);
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
    return "FalsifyCompositeGate [input=" + input + ", output=" + output + "]";
  }

}
