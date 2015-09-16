package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.bool.primitive.AndPrimitiveGate;

public class AndPrimitiveGateTest extends AbstractBinaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new AndPrimitiveGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, true },
        { true, false, false },
        { false, true, false },
        { false, false, false } };
  }
}
