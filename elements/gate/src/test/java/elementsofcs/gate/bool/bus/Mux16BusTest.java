package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.bus.Mux16Bus;

public class Mux16BusTest {
  protected final List<Pin> inputA = Pin.create16("inputA");
  protected final List<Pin> inputB = Pin.create16("inputB");
  protected final Pin selector = new Pin("Selector");
  protected final List<Pin> output = Pin.create16("output");

  protected Mux16Bus gate;
  protected boolean[][] truthTable;

  @Before
  public void setUp() {
    gate = new Mux16Bus(inputA, inputB, selector, output);
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

      inputA.forEach(p -> p.setValue(inputAValue));
      inputB.forEach(p -> p.setValue(inputBValue));
      selector.setValue(selectorValue);

      gate.eval();

      String msg = String.format("When A is %s, B is %s, and selector is %s, expected output is %s",
          inputAValue, inputBValue, selectorValue, expectedOutputValue);
      assertTrue(msg, output.stream().allMatch(p -> p.getValue() == expectedOutputValue));
    }
  }

}
