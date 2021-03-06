package elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class ENFlipFlopTest {

  private final Pin clockInput = new Pin();
  private final Pin inputD = new Pin();
  private final Pin load = new Pin();
  private final Pin outputQ = new Pin();

  private final ENFlipFlop dff = new ENFlipFlop(clockInput, inputD, load, outputQ);

  private final boolean[][] tt = new boolean[][] {
      // Clock | D | Load | Q' |
      { false, false, false, false }, // at reset, Q=false
      { true, false, false, false },
      { false, false, true, false },
      { true, false, true, false },
      { false, true, false, false }, // buffer true
      { true, true, false, false },
      { false, true, true, false },
      { true, false, true, true }, // write true, buffer false
      { true, false, false, true },
      { false, false, true, true },
      { true, false, true, false }, // write false
      { false, true, false, false }, // buffer true
      { true, true, false, false },
      { false, true, true, false },
      { true, true, true, true } // load true
  };

  @Test
  public void verifyTruthTable() {
    dff.reset();
    for (int i = 0; i < tt.length; i++) {
      clockInput.setValue(tt[i][0]);
      inputD.setValue(tt[i][1]);
      load.setValue(tt[i][2]);

      dff.eval();

      boolean expectedOutputValue = tt[i][3];
      assertTrue("At i=" + i + ", expecting outputQ=" + expectedOutputValue,
          expectedOutputValue == outputQ.getValue());
    }
  }
}
