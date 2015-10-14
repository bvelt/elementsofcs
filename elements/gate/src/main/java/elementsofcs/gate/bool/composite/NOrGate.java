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
public class NOrGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NotGate notAGate;
  private final NotGate notBGate;
  private final AndGate andGate;

  public NOrGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
    // NOT(A)
    notAGate = new NotGate(inputA);
    // NOT(B)
    notBGate = new NotGate(inputB);
    // AND(NOT(A), NOT(B))
    andGate = new AndGate(notAGate.getOutput(), notBGate.getOutput(), output);
  }

  public NOrGate(Pin inputA, Pin inputB) {
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
    return "NOrGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
