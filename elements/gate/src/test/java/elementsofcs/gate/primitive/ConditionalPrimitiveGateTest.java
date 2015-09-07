package elementsofcs.gate.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.primitive.ConditionalPrimitiveGate;

public class ConditionalPrimitiveGateTest extends AbstractPrimitiveGateTest {

  @Override
  protected Gate createGate() {
    return new ConditionalPrimitiveGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, true },
        { true, false, false },
        { false, true, true },
        { false, false, true } };
  }

}
