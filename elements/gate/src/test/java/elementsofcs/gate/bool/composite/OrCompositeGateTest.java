package elementsofcs.gate.bool.composite;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.bool.composite.OrCompositeGate;

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
