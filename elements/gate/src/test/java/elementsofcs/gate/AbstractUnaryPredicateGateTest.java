package elementsofcs.gate;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public abstract class AbstractUnaryPredicateGateTest {
  protected final Pin input = new Pin("Input");
  protected final Pin output = new Pin("Output");

  protected Gate gate;
  protected boolean[][] truthTable;

  @Before
  public void setUp() {
    gate = createGate();
    truthTable = createTruthTable();
  }

  protected abstract boolean[][] createTruthTable();

  protected abstract Gate createGate();

  @Test
  public void outputsShouldMatchExpectedOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {
      boolean inputAValue = truthTable[i][0];
      boolean expectedOutputValue = truthTable[i][1];

      gate.reset();

      input.setValue(inputAValue);

      gate.eval();

      String msg = String.format("When Input is %s, expected output is %s",
          inputAValue, expectedOutputValue);
      assertTrue(msg, expectedOutputValue == output.getValue());
    }
  }
}