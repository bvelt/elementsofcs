package elementsofcs.gate.bool.composite;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.TruthTables;

public class MuxGateTest {

  private final Pin inputA = new Pin();
  private final Pin inputB = new Pin();
  private final Pin selector = new Pin();
  private final Pin output = new Pin();

  private MuxGate gate = new MuxGate(inputA, inputB, selector, output);
  private boolean[][] tt = TruthTables.MUX;

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputAValue = tt[i][0];
      boolean inputBValue = tt[i][1];
      boolean selectorValue = tt[i][2];

      boolean expectedOutputValue = tt[i][3];

      gate.reset();

      inputA.setValue(inputAValue);
      inputB.setValue(inputBValue);
      selector.setValue(selectorValue);

      gate.eval();

      String msg = String.format("When A is %s, B is %s, and selector is %s, expected output is %s",
          inputAValue, inputBValue, selectorValue, expectedOutputValue);
      assertTrue(msg, expectedOutputValue == output.getValue());
    }
  }

}
