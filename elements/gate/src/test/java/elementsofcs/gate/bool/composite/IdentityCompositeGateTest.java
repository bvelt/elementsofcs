package elementsofcs.gate.bool.composite;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractUnaryPredicateGateTest;
import elementsofcs.gate.bool.TruthTables;

public class IdentityCompositeGateTest extends AbstractUnaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new IdentityCompositeGate(input, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return TruthTables.IDENTITY;
  }

}
