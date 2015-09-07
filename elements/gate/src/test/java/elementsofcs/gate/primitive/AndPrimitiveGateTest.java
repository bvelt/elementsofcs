package elementsofcs.gate.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.primitive.AndPrimitiveGate;

public class AndPrimitiveGateTest extends AbstractPrimitiveGateTest {
  @Override
  protected Gate createGate() {
    return new AndPrimitiveGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, true },
        { true, false, false },
        { false, true, false },
        { false, false, false } };
  }
}
