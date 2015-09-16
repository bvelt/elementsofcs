package elementsofcs.gate.bool.composite;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractUnaryPredicateGateTest;
import elementsofcs.gate.bool.composite.NotCompositeGate;

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
