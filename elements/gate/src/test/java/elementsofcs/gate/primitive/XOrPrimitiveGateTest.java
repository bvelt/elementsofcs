package elementsofcs.gate.primitive;

import elementsofcs.gate.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.Gate;

public class XOrPrimitiveGateTest extends AbstractBinaryPredicateGateTest {

  @Override
  protected Gate createGate() {
    return new XOrPrimitiveGate(inputA, inputB, output);
  }

  @Override
  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, false },
        { true, false, true },
        { false, true, true },
        { false, false, false } };
  }

}
