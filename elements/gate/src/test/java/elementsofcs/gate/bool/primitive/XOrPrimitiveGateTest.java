package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Gate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGateTest;
import elementsofcs.gate.bool.primitive.XOrPrimitiveGate;

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
