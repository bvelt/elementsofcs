package elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class DFFTest {

  private final Pin input = new Pin("input");
  private final Pin output = new Pin("output");

  private final DFF gate = new DFF(input, output);

  // | In | In Next | Out |
  private final boolean[][] tt = new boolean[][] {
      { false, false, false },
      { false, true, false },
      { true, false, true },
      { true, true, true }
  };

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean input1stValue = tt[i][0];
      boolean input2ndValue = tt[i][1];

      boolean expectedOutputValue = tt[i][2];

      gate.reset();

      input.setValue(input1stValue);
      gate.eval(); // load
      assertTrue("At i=" + i + ", when first input, expecting output=false", output.getValue() == false);

      input.setValue(input2ndValue);
      gate.eval();

      String msg = "At i=" + i + ", In[0]=" + input1stValue + ", In[1]=" + input2ndValue + ", expecting Out=" + expectedOutputValue;
      assertTrue(msg, output.getValue() == expectedOutputValue);
    }
  }
}
