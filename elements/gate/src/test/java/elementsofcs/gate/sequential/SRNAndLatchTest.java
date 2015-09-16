package elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class SRNAndLatchTest {

  private final Pin inputS = new Pin("S");
  private final Pin inputR = new Pin("R");
  private final Pin outputQ = new Pin("Q");

  private final SRNAndLatch gate = new SRNAndLatch(inputS, inputR, outputQ);

  // | S | R | Q | Q' |
  private final boolean[][] truthTable = new boolean[][] {
      { false, true, false, true },
      { false, true, true, true },
      { true, false, false, false },
      { true, false, true, false },
      { true, true, false, false },
      { true, true, true, true }
  };

  @Test
  public void verifyOutputsMatchExpectedOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {
      gate.reset();

      boolean inputSValue = truthTable[i][0];
      boolean inputRValue = truthTable[i][1];
      boolean outputQValue = truthTable[i][2];

      boolean expectedOutputQ = truthTable[i][3];

      inputS.setValue(inputSValue);
      inputR.setValue(inputRValue);
      outputQ.setValue(outputQValue);

      gate.eval();

      String msg = "At i=" + i + ", Q=" + outputQValue + ", expecting ";
      assertTrue(msg + "outputQ=" + expectedOutputQ, expectedOutputQ == outputQ.getValue());

    }
  }
}
