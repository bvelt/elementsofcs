package elementsofcs.gate.bool.composite;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;
import elementsofcs.gate.bool.primitive.NAndGate;

/**
 * AND composite gate composed of internal NOT and NAND gates
 * 
 * <pre>
 * AND(A, B) = NOT(NAND(A, B))
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class AndGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final NAndGate nandGate;
  private final NotGate notGate;

  public AndGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
    // NAND(A, B)
    nandGate = new NAndGate(inputA, inputB);
    // NOT(NAND(A, B))
    notGate = new NotGate(nandGate.getOutput(), output);
  }

  public AndGate(Pin inputA, Pin inputB) {
    this(inputA, inputB, new Pin());
  }

  @Override
  public void eval() {
    nandGate.eval();
    notGate.eval();
  }

  @Override
  public void reset() {
    nandGate.reset();
    notGate.reset();
  }

  @Override
  public String toString() {
    return "AndGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
