package elementsofcs.gate;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;

public abstract class AbstractBinaryPredicateGateTest {
  protected final Pin inputA = new Pin("A");
  protected final Pin inputB = new Pin("B");
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
      boolean inputBValue = truthTable[i][1];
      boolean expectedOutputValue = truthTable[i][2];

      gate.reset();

      inputA.setValue(inputAValue);
      inputB.setValue(inputBValue);

      gate.eval();

      String msg = String.format("When A is %s and B is %s, expected output is %s",
          inputAValue, inputBValue, expectedOutputValue);
      assertTrue(msg, expectedOutputValue == output.getValue());
    }
  }
}
