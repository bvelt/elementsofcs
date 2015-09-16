package elementsofcs.gate.bool.composite;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.bool.composite.ConditionalCompositeGate;

public class ConditionalCompositeGateTest extends AbstractBinaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new ConditionalCompositeGate(inputA, inputB, output);
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
