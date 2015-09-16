package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.bool.primitive.OrPrimitiveGate;

public class OrPrimitiveGateTest extends AbstractBinaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new OrPrimitiveGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, true },
        { true, false, true },
        { false, true, true },
        { false, false, false } };
  }

}
