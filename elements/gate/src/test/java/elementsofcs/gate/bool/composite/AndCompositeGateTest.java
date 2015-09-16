package elementsofcs.gate.bool.composite;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.bool.composite.AndCompositeGate;

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
