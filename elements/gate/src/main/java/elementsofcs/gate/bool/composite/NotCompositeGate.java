package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.UnaryPredicateGate;
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
public class NotCompositeGate implements UnaryPredicateGate, CompositeGate {

  private final NAndPrimitiveGate nand;

  public NotCompositeGate(Pin input, Pin output) {
    nand = new NAndPrimitiveGate(input, input, output);
  }

  @Override
  public Pin getInput() {
    return nand.getInputA();
  }

  @Override
  public Pin getOutput() {
    return nand.getOutput();
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
