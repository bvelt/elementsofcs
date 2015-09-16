package elementsofcs.gate.bool.alu;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.alu.HalfAdderGate;

public class HalfAdderGateTest {

  private final Pin inputA = new Pin("inputA");
  private final Pin inputB = new Pin("inputB");
  private final Pin sum = new Pin("sum");
  private final Pin carry = new Pin("carry");

  private final HalfAdderGate gate = new HalfAdderGate(inputA, inputB, carry, sum);

  private final boolean[][] truthTable = new boolean[][] {
      { false, false, false, false },
      { false, true, false, true },
      { true, false, false, true },
      { true, true, true, false }
  };

  @Test
  public void verifyOutputsMatchOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {
      gate.reset();

      boolean inputAValue = truthTable[i][0];
      boolean inputBValue = truthTable[i][1];
      boolean expectedCarryValue = truthTable[i][2];
      boolean expectedSumValue = truthTable[i][3];

      gate.getInputA().setValue(inputAValue);
      gate.getInputB().setValue(inputBValue);

      gate.eval();

      assertTrue("When A is " + inputAValue + " and B is " + inputBValue + ", expected carry is " + expectedCarryValue,
          expectedCarryValue == gate.getCarry().getValue());
      assertTrue("When A is " + inputAValue + " and B is " + inputBValue + ", expected sum is " + expectedSumValue,
          expectedSumValue == gate.getSum().getValue());
    }
  }
}
