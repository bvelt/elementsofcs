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

  private final NAndPrimitiveGate notInputAndInputGate;

  public NotCompositeGate(Pin input, Pin output) {
    super();
    // NAND(In, In)
    notInputAndInputGate = new NAndPrimitiveGate(input, input, output);
  }

  @Override
  public Pin getInput() {
    return notInputAndInputGate.getInputA();
  }

  @Override
  public Pin getOutput() {
    return notInputAndInputGate.getOutput();
  }

  @Override
  public void eval() {
    notInputAndInputGate.eval();
  }

  @Override
  public void reset() {
    notInputAndInputGate.reset();
  }

  @Override
  public String toString() {
    return "NotCompositeGate [getInput()=" + getInput() + ", getOutput()=" + getOutput() + "]";
  }

}
