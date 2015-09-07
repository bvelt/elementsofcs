package elementsofcs.gate.composite;

import elementsofcs.gate.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.Gate;

public class OrCompositeGateTest extends AbstractBinaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new OrCompositeGate(inputA, inputB, output);
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
