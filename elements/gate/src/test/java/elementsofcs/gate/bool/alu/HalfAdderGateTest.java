package elementsofcs.gate.bool.alu;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class HalfAdderGateTest {

  private final Pin inputA = new Pin("inputA");
  private final Pin inputB = new Pin("inputB");
  private final Pin sum = new Pin("sum");
  private final Pin carry = new Pin("carry");

  private final HalfAdderGate gate = new HalfAdderGate(inputA, inputB, carry, sum);

  private final boolean[][] tt = new boolean[][] {
      { false, false, false, false },
      { false, true, false, true },
      { true, false, false, true },
      { true, true, true, false }
  };

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputAValue = tt[i][0];
      boolean inputBValue = tt[i][1];

      boolean expectedCarryValue = tt[i][2];
      boolean expectedSumValue = tt[i][3];

      gate.reset();

      inputA.setValue(inputAValue);
      inputB.setValue(inputBValue);

      gate.eval();

      assertTrue("When A is " + inputAValue + " and B is " + inputBValue + ", expected carry is " + expectedCarryValue,
          expectedCarryValue == carry.getValue());
      assertTrue("When A is " + inputAValue + " and B is " + inputBValue + ", expected sum is " + expectedSumValue,
          expectedSumValue == sum.getValue());
    }
  }
}
