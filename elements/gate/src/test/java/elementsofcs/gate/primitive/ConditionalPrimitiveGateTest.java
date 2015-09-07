package elementsofcs.gate.primitive;

import elementsofcs.gate.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.Gate;

public class ConditionalPrimitiveGateTest extends AbstractBinaryPredicateGateTest {

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
