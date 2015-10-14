package elementsofcs.gate.bool.composite;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.bool.TruthTables;

public class AndGateTest extends AbstractBinaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new AndGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return TruthTables.AND;
  }

}
