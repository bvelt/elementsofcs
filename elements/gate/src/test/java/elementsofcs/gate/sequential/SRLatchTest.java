package elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class SRLatchTest {

  private final Pin inputS = new Pin("inputS");
  private final Pin inputR = new Pin("inputR");
  private final Pin outputQ = new Pin("outputQ");
  private final Pin outputNQ = new Pin("outputNQ");

  private final SRLatch latch = new SRLatch(inputS, inputR, outputQ, outputNQ);

  private final boolean[][] tt = new boolean[][] {
      // | S | R | Q | Q' |
      // S=R=0, Q'=Q
      { false, false, false, false },
      { false, false, true, true },
      // R=1, Q'=0
      { false, true, true, false },
      { false, true, false, false },
      // S=1, Q'=0
      { true, false, false, true },
      { true, false, true, true }
  };

  @Test(expected = IllegalStateException.class)
  public void assertingSetAndResetShouldThrowIllegalStateExceptionOnEvaluation() {
    latch.reset();
    inputS.setValue(true);
    inputR.setValue(true);
    latch.eval();
  }

  @Test(expected = IllegalStateException.class)
  public void equalOutputsShouldThrowIllegalStateExceptionOnEvaluation() {
    latch.reset();
    outputQ.setValue(true);
    outputNQ.setValue(true);
    latch.eval();
  }

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputSValue = tt[i][0];
      boolean inputRValue = tt[i][1];
      boolean outputQValue = tt[i][2];

      boolean expectedOutputQValue = tt[i][3];
      boolean expectedOutputNQValue = !expectedOutputQValue;

      latch.reset();

      inputS.setValue(inputSValue);
      inputR.setValue(inputRValue);
      outputQ.setValue(outputQValue);
      outputNQ.setValue(!outputQValue);

      latch.eval();

      assertTrue("At i=" + i + ", expecting Q=" + expectedOutputQValue, expectedOutputQValue == outputQ.getValue());
      assertTrue("At i=" + i + ", expecting NQ=" + expectedOutputNQValue, expectedOutputNQValue == outputNQ.getValue());
    }
  }
}
