package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

/**
 * NOR composite gate composed of internal gates
 * 
 * <pre>
 * NOR(A, B) = AND(NOT(A), NOT(B))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class NOrCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NotCompositeGate notAGate;
  private final NotCompositeGate notBGate;
  private final AndCompositeGate andGate;

  public NOrCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
    // NOT(A)
    notAGate = new NotCompositeGate(inputA);
    // NOT(B)
    notBGate = new NotCompositeGate(inputB);
    // AND(NOT(A), NOT(B))
    andGate = new AndCompositeGate(notAGate.getOutput(), notBGate.getOutput(), output);
  }

  public NOrCompositeGate(Pin inputA, Pin inputB) {
    this(inputA, inputB, new Pin());
  }

  @Override
  public void eval() {
    notAGate.eval();
    notBGate.eval();
    andGate.eval();
  }

  @Override
  public void reset() {
    notAGate.reset();
    notBGate.reset();
    andGate.reset();
  }

  @Override
  public String toString() {
    return "OrCompositeGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
