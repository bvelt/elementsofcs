package elementsofcs.gate.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.primitive.NotAPrimitiveGate;

public class NotAPrimitiveGateTest extends AbstractPrimitiveGateTest {

  @Override
  protected Gate createGate() {
    return new NotAPrimitiveGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, false },
        { true, false, false },
        { false, true, true },
        { false, false, true } };
  }

}
