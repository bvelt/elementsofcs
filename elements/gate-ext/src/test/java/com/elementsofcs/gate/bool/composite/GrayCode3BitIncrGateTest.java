package com.elementsofcs.gate.bool.composite;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class GrayCode3BitIncrGateTest {

  private final Pin inputA = new Pin();
  private final Pin inputB = new Pin();
  private final Pin inputC = new Pin();

  private final Pin outputX = new Pin();
  private final Pin outputY = new Pin();
  private final Pin outputZ = new Pin();

  private final GrayCode3BitIncrGate gate = new GrayCode3BitIncrGate(inputA, inputB, inputC, outputX, outputY, outputZ);

  private final boolean[][] tt = new boolean[][] {
      // | A | B | C | X | Y | Z |
      { false, false, false, false, false, true },
      { false, false, true, false, true, true },
      { false, true, true, false, true, false },
      { false, true, false, true, true, false },
      { true, true, false, true, true, true },
      { true, true, true, true, false, true },
      { true, false, true, true, false, false }
  };

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputAValue = tt[i][0];
      boolean inputBValue = tt[i][1];
      boolean inputCValue = tt[i][2];

      boolean expectedOutputXValue = tt[i][3];
      boolean expectedOutputYValue = tt[i][4];
      boolean expectedOutputZValue = tt[i][5];

      gate.reset();

      inputA.setValue(inputAValue);
      inputB.setValue(inputBValue);
      inputC.setValue(inputCValue);

      gate.eval();

      assertTrue("At i=" + i + ", expecting outputX=" + expectedOutputXValue, expectedOutputXValue == outputX.getValue());
      assertTrue("At i=" + i + ", expecting outputY=" + expectedOutputYValue, expectedOutputYValue == outputY.getValue());
      assertTrue("At i=" + i + ", expecting outputZ=" + expectedOutputZValue, expectedOutputZValue == outputZ.getValue());
    }
  }
}
