package elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class PrimitiveDFFTest {

  private final Pin input = new Pin("In");
  private final Pin output = new Pin("Out");

  private final PrimitiveDFF gate = new PrimitiveDFF(input, output);

  // | In | Out |
  private final boolean[][] truthTable = new boolean[][] {
      { false, false },
      { true, false },
      { true, true },
      { true, true },
      { false, true },
      { false, false }
  };

  @Test
  public void verifyOutputsMatchExpectedOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {

      boolean inputValue = truthTable[i][0];
      boolean expectedOutputValue = truthTable[i][1];

      input.setValue(inputValue);

      gate.eval();

      String msg = "At i=" + i + ", when In=" + inputValue + ", expecting Out=" + expectedOutputValue;
      assertTrue(msg, output.getValue() == expectedOutputValue);
    }
  }
}
