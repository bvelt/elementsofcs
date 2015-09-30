package elementsofcs.gate.bool.bus;

import elementsofcs.gate.bool.TruthTables;

public class AndBusTest extends AbstractBinaryPredicateBusTest {

  @Override
  protected Bus createBus() {
    return AndBus.create16(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return TruthTables.AND;
  }

}
