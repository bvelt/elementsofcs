package elementsofcs.gate.bool;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;

public abstract class AbstractUnaryPredicateGateTest {

  protected final Pin input = new Pin("Input");
  protected final Pin output = new Pin("Output");

  protected Gate gate;
  protected boolean[][] tt;

  @Before
  public void setUp() {
    gate = createGate();
    tt = createTruthTable();
  }

  protected abstract boolean[][] createTruthTable();

  protected abstract Gate createGate();

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputAValue = tt[i][0];

      boolean expectedOutputValue = tt[i][1];

      gate.reset();

      input.setValue(inputAValue);

      gate.eval();

      String msg = String.format("When input is %s, expected output is %s",
          inputAValue, expectedOutputValue);
      assertTrue(msg, expectedOutputValue == output.getValue());
    }
  }
}
