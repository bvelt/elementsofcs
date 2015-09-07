package elementsofcs.gate.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.primitive.NotBPrimitiveGate;

public class NotBPrimitiveGateTest extends AbstractPrimitiveGateTest {

  @Override
  protected Gate createGate() {
    return new NotBPrimitiveGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, false },
        { true, false, true },
        { false, true, false },
        { false, false, true } };
  }

}
