package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractUnaryPredicateGate;
import elementsofcs.gate.bool.primitive.NAndPrimitiveGate;

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
public class NotCompositeGate extends AbstractUnaryPredicateGate implements CompositeGate {

  private final NAndPrimitiveGate nandGate;

  public NotCompositeGate(Pin input, Pin output) {
    super(input, output);
    // NAND(In, In)
    nandGate = new NAndPrimitiveGate(input, input, output);
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
    return "NotCompositeGate [input=" + input + ", output=" + output + "]";
  }

}
