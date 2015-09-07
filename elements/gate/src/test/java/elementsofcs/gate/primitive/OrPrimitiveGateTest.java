package elementsofcs.gate.primitive;

import elementsofcs.gate.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.Gate;

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
