package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractUnaryPredicateGate;
import elementsofcs.gate.bool.primitive.NAndGate;

/**
 * NOT composite gate composed of internal NAND gate
 * 
 * <pre>
 * NOT(A) = NAND(A, A)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class NotGate extends AbstractUnaryPredicateGate implements CompositeGate {

  private final NAndGate nandGate;

  public NotGate(Pin input, Pin output) {
    super(input, output);
    // NAND(In, In)
    nandGate = new NAndGate(input, input, output);
  }

  public NotGate(Pin input) {
    this(input, new Pin());
  }

  @Override
  public void eval() {
    nandGate.eval();
  }

  @Override
  public void reset() {
    nandGate.reset();
  }

  @Override
  public String toString() {
    return "NotGate [input=" + input + ", output=" + output + "]";
  }

}
