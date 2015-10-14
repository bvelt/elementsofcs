package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;
import elementsofcs.gate.bool.primitive.NAndGate;

/**
 * OR composite gate composed of internal gates
 * 
 * <pre>
 * OR(A, B) = NAND(NOT(A), NOT(B))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class OrGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NotGate notAGate;
  private final NotGate notBGate;
  private final NAndGate nandGate;

  public OrGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
    // NOT(A)
    notAGate = new NotGate(inputA);
    // NOT(B)
    notBGate = new NotGate(inputB);
    // NAND(NOT(A), NOT(B))
    nandGate = new NAndGate(notAGate.getOutput(), notBGate.getOutput(), output);
  }

  public OrGate(Pin inputA, Pin inputB) {
    this(inputA, inputB, new Pin());
  }

  @Override
  public void eval() {
    notAGate.eval();
    notBGate.eval();
    nandGate.eval();
  }

  @Override
  public void reset() {
    notAGate.reset();
    notBGate.reset();
    nandGate.reset();
  }

  @Override
  public String toString() {
    return "OrGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
