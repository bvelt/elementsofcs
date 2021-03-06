package elementsofcs.gate.bool.bus;

import elementsofcs.gate.bool.TruthTables;

public class OrBusTest extends AbstractBinaryPredicateBusTest {

  @Override
  protected Bus createBus() {
    return OrBus.create16(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return TruthTables.OR;
  }

}
