package elementsofcs.gate.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.primitive.XOrPrimitiveGate;

public class XOrPrimitiveGateTest extends AbstractPrimitiveGateTest {

  @Override
  protected Gate createGate() {
    return new XOrPrimitiveGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, false },
        { true, false, true },
        { false, true, true },
        { false, false, false } };
  }

}
