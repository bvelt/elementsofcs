package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.bool.primitive.ConditionalPrimitiveGate;

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
