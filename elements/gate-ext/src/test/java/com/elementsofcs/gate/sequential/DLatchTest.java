package com.elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class DLatchTest {

  private final Pin clockInput = new Pin("clockInput");
  private final Pin inputD = new Pin("inputD");
  private final Pin outputQ = new Pin("outputQ");
  private final Pin outputNQ = new Pin("outputNQ");

  private final DLatch latch = new DLatch(clockInput, inputD, outputQ, outputNQ);

  private final boolean[][] tt = new boolean[][] {
      // CLK | D | Q | NQ | Q' | NQ' |
      { false, false, false, true, false, true },
      { false, false, true, false, true, false },
      { false, true, false, true, false, true },
      { false, true, true, false, true, false },
      { true, false, false, true, false, true },
      { true, false, true, false, false, true },
      { true, true, false, true, true, false },
      { true, true, true, false, true, false }
  };

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean clockPhaseValue = tt[i][0];
      boolean inputDValue = tt[i][1];
      boolean outputQValue = tt[i][2];
      boolean outputNQValue = tt[i][3];

      boolean expectedOutputQValue = tt[i][4];
      boolean expectedOutputNQValue = tt[i][5];

      latch.reset();

      inputD.setValue(inputDValue);
      outputQ.setValue(outputQValue);
      outputNQ.setValue(outputNQValue);

      latch.onClockSignal(clockPhaseValue);

      assertTrue("At i=" + i + ", expecting Q=" + expectedOutputQValue, expectedOutputQValue == outputQ.getValue());
      assertTrue("At i=" + i + ", expecting NQ=" + expectedOutputNQValue, expectedOutputNQValue == outputNQ.getValue());
    }
  }
}
