package com.elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class DFlipFlopTest {

  private final Pin clockInput = new Pin("clockInput");
  private final Pin inputD = new Pin("inputD");
  private final Pin outputQ = new Pin("outputQ");

  private final DFlipFlop dff = new DFlipFlop(clockInput, inputD, outputQ);

  private final boolean[][] tt = new boolean[][] {
      // CLK | D | Q' |
      { false, false, false }, // buffer false in master, slave opaque
      { true, false, false }, // write false to Q regardless of D
      { true, true, false }, // write false to Q regardless of D
      { false, true, false }, // buffer true in master, slave opaque
      { true, false, true }, // write true to Q regardless of D
      { true, false, true } // write true to Q regardless of D
  };

  @Test
  public void verifyTruthTable() {
    dff.reset();
    for (int i = 0; i < tt.length; i++) {
      boolean clockPhaseValue = tt[i][0];
      boolean inputDValue = tt[i][1];
      boolean outputQValue = tt[i][2];

      inputD.setValue(inputDValue);

      dff.onClockSignal(clockPhaseValue);

      assertTrue("At i=" + i + ", expecting Q=" + outputQValue, outputQValue == outputQ.getValue());
    }
  }
}
