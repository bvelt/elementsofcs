package elementsofcs.gate.bool.bus;

import elementsofcs.gate.bool.TruthTables;

public class XOrBusTest extends AbstractBinaryPredicateBusTest {

  @Override
  protected Bus createBus() {
    return XOrBus.create16(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return TruthTables.XOR;
  }

}
