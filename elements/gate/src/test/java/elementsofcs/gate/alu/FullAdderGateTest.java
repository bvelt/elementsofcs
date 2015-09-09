package elementsofcs.gate.alu;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class FullAdderGateTest {

  private final Pin inputA = new Pin("inputA");
  private final Pin inputB = new Pin("inputB");
  private final Pin inputC = new Pin("inputC");
  private final Pin sum = new Pin("sum");
  private final Pin carry = new Pin("carry");

  private final FullAdderGate gate = new FullAdderGate(inputA, inputB, inputC, carry, sum);

  private final boolean[][] truthTable = new boolean[][] {
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
  public void verifyOutputsMatchOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {
      gate.reset();

      boolean inputAValue = truthTable[i][0];
      boolean inputBValue = truthTable[i][1];
      boolean inputCValue = truthTable[i][2];
      boolean expectedCarryValue = truthTable[i][3];
      boolean expectedSumValue = truthTable[i][4];

      gate.getInputA().setValue(inputAValue);
      gate.getInputB().setValue(inputBValue);
      gate.getInputC().setValue(inputCValue);

      gate.eval();

      assertTrue("When A is " + inputAValue + ", B is " + inputBValue + ", and C is " + inputCValue + ", expected carry is " + expectedCarryValue,
          expectedCarryValue == gate.getCarry().getValue());
      assertTrue("When A is " + inputAValue + ", B is " + inputBValue + ", and C is " + inputCValue + ", expected sum is " + expectedSumValue,
          expectedSumValue == gate.getSum().getValue());
    }
  }
}
