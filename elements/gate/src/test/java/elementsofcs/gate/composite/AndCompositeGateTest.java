package elementsofcs.gate.composite;

import elementsofcs.gate.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.Gate;

public class AndCompositeGateTest extends AbstractBinaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new AndCompositeGate(inputA, inputB, output);
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
