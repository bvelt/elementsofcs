package elementsofcs.gate.bool.alu;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class FullAdderGateTest {

  private final Pin inputA = new Pin();
  private final Pin inputB = new Pin();
  private final Pin inputC = new Pin();
  private final Pin sum = new Pin();
  private final Pin carry = new Pin();

  private final FullAdderGate gate = new FullAdderGate(inputA, inputB, inputC, carry, sum);

  private final boolean[][] tt = new boolean[][] {
      { false, false, false, false, false },
      { false, false, true, false, true },
      { false, true, false, false, true },
      { false, true, true, true, false },
      { true, false, false, false, true },
      { true, false, true, true, false },
      { true, true, false, true, false },
      { true, true, true, true, true }
  };

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputAValue = tt[i][0];
      boolean inputBValue = tt[i][1];
      boolean inputCValue = tt[i][2];

      boolean expectedCarryValue = tt[i][3];
      boolean expectedSumValue = tt[i][4];

      gate.reset();

      inputA.setValue(inputAValue);
      inputB.setValue(inputBValue);
      inputC.setValue(inputCValue);

      gate.eval();

      assertTrue("When A is " + inputAValue + ", B is " + inputBValue + ", and C is " + inputCValue + ", expected carry is " + expectedCarryValue,
          expectedCarryValue == carry.getValue());
      assertTrue("When A is " + inputAValue + ", B is " + inputBValue + ", and C is " + inputCValue + ", expected sum is " + expectedSumValue,
          expectedSumValue == sum.getValue());
    }
  }
}
