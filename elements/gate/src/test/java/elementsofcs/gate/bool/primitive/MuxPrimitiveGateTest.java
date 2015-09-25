package elementsofcs.gate.bool.primitive;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class MuxPrimitiveGateTest {

  private final Pin inputA = new Pin("A");
  private final Pin inputB = new Pin("B");
  private final Pin selector = new Pin("Selector");
  private final Pin output = new Pin("Output");

  private MuxPrimitiveGate gate = new MuxPrimitiveGate(inputA, inputB, selector, output);

  private boolean[][] tt = new boolean[][] {
      { true, true, true, true },
      { true, true, false, true },
      { true, false, true, true },
      { true, false, false, false },
      { false, true, true, false },
      { false, true, false, true },
      { false, false, true, false },
      { false, false, false, false } };;

  @Test
  public void outputsShouldMatchExpectedOutputsInTruthTable() {
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
