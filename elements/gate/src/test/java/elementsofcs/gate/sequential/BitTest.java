package elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class BitTest {

  private final Pin input = new Pin("in");
  private final Pin output = new Pin("out");
  private final Pin load = new Pin("load");

  private final Bit bit = new Bit(input, load, output);

  // | In(t - 1) | Out(t - 1) | Load(t - 1) | Out(t) (when Load(t)=0) |
  private final boolean[][] tt = new boolean[][] {
      { false, false, false, false },
      { false, false, true, false },
      { false, true, false, true },
      { false, true, true, false },
      { true, false, true, true },
      { true, false, false, false },
      { true, true, true, true },
      { true, true, false, true }
  };

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputValue = tt[i][0];
      boolean outputValue = tt[i][1];
      boolean loadValue = tt[i][2];

      boolean expectedOutNextValue = tt[i][3];

      bit.reset();

      input.setValue(inputValue);
      output.setValue(outputValue);
      load.setValue(loadValue);

      // at time t - 1, store either output or input in dff internal bit
      bit.eval();

      // at time t, send dff's internal bit to output
      load.setValue(false);
      bit.eval();

      assertTrue("At i=" + i + ", expecting out=" + expectedOutNextValue,
          expectedOutNextValue == output.getValue());
    }
  }
}
