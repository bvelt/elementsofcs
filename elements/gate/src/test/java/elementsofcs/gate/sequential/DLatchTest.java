package elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class DLatchTest {

  private final Pin clockInput = new Pin();
  private final Pin inputD = new Pin();
  private final Pin outputQ = new Pin();
  private final Pin outputNQ = new Pin();

  private final DLatch latch = new DLatch(clockInput, inputD, outputQ, outputNQ);

  private final boolean[][] tt = new boolean[][] {
      // CLK | D | Q | Q' |
      { false, false, false, false },
      { false, false, true, true },
      { false, true, false, false },
      { false, true, true, true },
      { true, false, false, false },
      { true, false, true, false },
      { true, true, false, true },
      { true, true, true, true }
  };

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean clockPhaseValue = tt[i][0];
      boolean inputDValue = tt[i][1];
      boolean outputQValue = tt[i][2];

      boolean expectedOutputQValue = tt[i][3];
      boolean expectedOutputNQValue = !expectedOutputQValue;

      latch.reset();

      clockInput.setValue(clockPhaseValue);
      inputD.setValue(inputDValue);
      outputQ.setValue(outputQValue);
      outputNQ.setValue(!outputQValue);

      latch.eval();

      assertTrue("At i=" + i + ", expecting Q=" + expectedOutputQValue, expectedOutputQValue == outputQ.getValue());
      assertTrue("At i=" + i + ", expecting NQ=" + expectedOutputNQValue, expectedOutputNQValue == outputNQ.getValue());
    }
  }
}
