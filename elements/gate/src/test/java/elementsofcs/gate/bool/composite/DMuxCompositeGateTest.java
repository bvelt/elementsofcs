package elementsofcs.gate.bool.composite;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class DMuxCompositeGateTest {

  private final Pin input = new Pin("Input");
  private final Pin selector = new Pin("Selector");
  private final Pin outputA = new Pin("OutputA");
  private final Pin outputB = new Pin("OutputB");

  private DMuxCompositeGate gate = new DMuxCompositeGate(input, selector, outputA, outputB);

  private boolean[][] tt = new boolean[][] {
      { true, true, true, false },
      { true, false, false, true },
      { false, false, false, false },
      { false, true, false, false } };;

  @Test
  public void outputsShouldMatchExpectedOutputsInTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputValue = tt[i][0];
      boolean selectorValue = tt[i][1];
      boolean outputAValue = tt[i][2];
      boolean outputBValue = tt[i][3];

      gate.reset();

      input.setValue(inputValue);
      selector.setValue(selectorValue);

      gate.eval();

      String msg = String.format("When input is %s and selector is %s, expected output A is %s and output B is %s",
          inputValue, selectorValue, outputAValue, outputBValue);
      assertTrue(msg, outputAValue == outputA.getValue());
      assertTrue(msg, outputBValue == outputB.getValue());
    }
  }

}
