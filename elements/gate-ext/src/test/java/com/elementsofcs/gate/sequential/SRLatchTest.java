package com.elementsofcs.gate.sequential;

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
      // | S | R | Q | NQ | Q' | NQ' |
      { false, false, false, true, false, true },
      { false, false, true, false, true, false },
      { false, true, true, false, false, true },
      { false, true, false, true, false, true },
      { true, false, false, true, true, false },
      { true, false, true, false, true, false },
      { true, true, false, true, false, false },
      { true, true, true, false, false, false }
  };

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputSValue = tt[i][0];
      boolean inputRValue = tt[i][1];
      boolean outputQValue = tt[i][2];
      boolean outputNQValue = tt[i][3];

      boolean expectedOutputQValue = tt[i][4];
      boolean expectedOutputNQValue = tt[i][5];

      latch.reset();

      inputS.setValue(inputSValue);
      inputR.setValue(inputRValue);
      outputQ.setValue(outputQValue);
      outputNQ.setValue(outputNQValue);

      latch.eval();

      assertTrue("At i=" + i + ", expecting Q=" + expectedOutputQValue, expectedOutputQValue == outputQ.getValue());
      assertTrue("At i=" + i + ", expecting NQ=" + expectedOutputNQValue, expectedOutputNQValue == outputNQ.getValue());
    }
  }
}
