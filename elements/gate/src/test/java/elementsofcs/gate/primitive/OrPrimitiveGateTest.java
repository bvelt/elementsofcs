package elementsofcs.gate.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.primitive.OrPrimitiveGate;

public class OrPrimitiveGateTest extends AbstractPrimitiveGateTest {

  @Override
  protected Gate createGate() {
    return new OrPrimitiveGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, true },
        { true, false, true },
        { false, true, true },
        { false, false, false } };
  }

}
