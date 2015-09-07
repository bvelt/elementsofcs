package elementsofcs.gate.composite;

import elementsofcs.gate.AbstractUnaryPredicateGateTest;
import elementsofcs.gate.Gate;

public class NotCompositeGateTest extends AbstractUnaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new NotCompositeGate(input, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, false },
        { false, true } };
  }

}
