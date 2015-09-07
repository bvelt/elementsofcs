package elementsofcs.gate.composite;

import elementsofcs.gate.AbstractUnaryPredicateGate;
import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.primitive.NAndPrimitiveGate;

/**
 * NOT composite gate composed of NAND gate.
 * 
 * NOT(X) = NAND(X,X)
 * 
 * @author brentvelthoen
 *
 */
public class NotCompositeGate extends AbstractUnaryPredicateGate implements CompositeGate {

  private final NAndPrimitiveGate nand;

  public NotCompositeGate(Pin input, Pin output) {
    super(input, output);
    nand = new NAndPrimitiveGate(input, input, output);
  }

  @Override
  public void eval() {
    nand.eval();
  }

  @Override
  public void reset() {
    nand.reset();
  }

  @Override
  public String toString() {
    return "NotCompositeGate [nand=" + nand + "]";
  }

}
