package elementsofcs.gate.bool.primitive;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.primitive.MultiplexorPrimitiveGate;

public class MultiplexorPrimitiveGateTest {
  protected final Pin inputA = new Pin("A");
  protected final Pin inputB = new Pin("B");
  protected final Pin selector = new Pin("Selector");
  protected final Pin output = new Pin("Output");

  protected MultiplexorPrimitiveGate gate;
  protected boolean[][] truthTable;

  @Before
  public void setUp() {
    gate = new MultiplexorPrimitiveGate(inputA, inputB, selector, output);
    truthTable = createTruthTable();
  }

  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, false, true, true },
        { false, true, false, true } };
  };

  @Test
  public void outputsShouldMatchExpectedOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {
      boolean inputAValue = truthTable[i][0];
      boolean inputBValue = truthTable[i][1];
      boolean selectorValue = truthTable[i][2];

      boolean expectedOutputValue = truthTable[i][3];

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
