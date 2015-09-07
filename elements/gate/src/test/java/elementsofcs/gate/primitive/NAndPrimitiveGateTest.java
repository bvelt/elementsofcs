package elementsofcs.gate.primitive;

import elementsofcs.gate.Gate;

public class NAndPrimitiveGateTest extends AbstractPrimitiveGateTest {
  @Override
  protected Gate createGate() {
    return new NAndPrimitiveGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, false },
        { true, false, true },
        { false, true, true },
        { false, false, true } };
  }
}
