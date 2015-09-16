package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.bool.primitive.NAndPrimitiveGate;

public class NAndPrimitiveGateTest extends AbstractBinaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new NAndPrimitiveGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, false },
        { true, false, true },
        { false, true, true },
        { false, false, true } };
  }
}
