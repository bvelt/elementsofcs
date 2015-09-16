package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractUnaryPredicateGateTest;
import elementsofcs.gate.bool.primitive.NotPrimitiveGate;

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
