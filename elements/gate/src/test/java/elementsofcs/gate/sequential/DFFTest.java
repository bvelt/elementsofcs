package elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class DFFTest {

  private final Pin input = new Pin("input");
  private final Pin output = new Pin("output");

  private final DFF gate = new DFF(input, output);

  // | In | In Next | Out |
  private final boolean[][] truthTable = new boolean[][] {
      { false, false, false },
      { false, true, false },
      { true, false, true },
      { true, true, true }
  };

  @Test
  public void verifyOutputsMatchExpectedOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {
      gate.reset();

      boolean input1stValue = truthTable[i][0];
      boolean input2ndValue = truthTable[i][1];
      boolean expectedOutputValue = truthTable[i][2];

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
