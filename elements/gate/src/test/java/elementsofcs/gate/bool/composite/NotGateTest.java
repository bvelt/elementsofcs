package elementsofcs.gate.bool.composite;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractUnaryPredicateGateTest;
import elementsofcs.gate.bool.TruthTables;

public class NotGateTest extends AbstractUnaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new NotGate(input, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return TruthTables.NOT;
  }

}
