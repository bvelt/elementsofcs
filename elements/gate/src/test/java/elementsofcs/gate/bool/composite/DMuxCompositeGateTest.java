package elementsofcs.gate.bool.composite;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.TruthTables;

public class DMuxCompositeGateTest {

  private final Pin input = new Pin();
  private final Pin selector = new Pin();
  private final Pin outputA = new Pin();
  private final Pin outputB = new Pin();

  private final DMuxCompositeGate gate = new DMuxCompositeGate(input, selector, outputA, outputB);
  private final boolean[][] tt = TruthTables.DMUX;

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputValue = tt[i][0];
      boolean selectorValue = tt[i][1];

      boolean expectedOutputAValue = tt[i][2];
      boolean expectedOutputBValue = tt[i][3];

      gate.reset();

      input.setValue(inputValue);
      selector.setValue(selectorValue);

      gate.eval();

      String msg = String.format("When input is %s and selector is %s, expected output A is %s and output B is %s",
          inputValue, selectorValue, expectedOutputAValue, expectedOutputBValue);
      assertTrue(msg, expectedOutputAValue == outputA.getValue());
      assertTrue(msg, expectedOutputBValue == outputB.getValue());
    }
  }

}
