package elementsofcs.gate.bool.composite;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractUnaryPredicateGateTest;

public class IdentityCompositeGateTest extends AbstractUnaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new IdentityCompositeGate(input, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true },
        { false, false } };
  }

}
