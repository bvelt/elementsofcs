package elementsofcs.gate.bool.composite;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.bool.TruthTables;

public class OrGateTest extends AbstractBinaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new OrGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return TruthTables.OR;
  }

}
