package elementsofcs.gate.primitive;

import elementsofcs.gate.AbstractUnaryPredicateGateTest;
import elementsofcs.gate.Gate;

public class NotPrimitiveGateTest extends AbstractUnaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new NotPrimitiveGate(input, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, false },
        { false, true } };
  }

}
