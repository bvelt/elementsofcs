package elementsofcs.gate.bool.primitive;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.primitive.DemultiplexorPrimitiveGate;

public class DemultiplexorPrimitiveGateTest {
  protected final Pin input = new Pin("Input");
  protected final Pin selector = new Pin("Selector");
  protected final Pin outputA = new Pin("OutputA");
  protected final Pin outputB = new Pin("OutputB");

  protected DemultiplexorPrimitiveGate gate;
  protected boolean[][] truthTable;

  @Before
  public void setUp() {
    gate = new DemultiplexorPrimitiveGate(input, selector, outputA, outputB);
    truthTable = createTruthTable();
  }

  protected boolean[][] createTruthTable() {
    return new boolean[][] {
        { true, true, true, false },
        { true, false, false, true } };
  };

  @Test
  public void outputsShouldMatchExpectedOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {
      boolean inputValue = truthTable[i][0];
      boolean selectorValue = truthTable[i][1];
      boolean outputAValue = truthTable[i][2];
      boolean outputBValue = truthTable[i][3];

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
