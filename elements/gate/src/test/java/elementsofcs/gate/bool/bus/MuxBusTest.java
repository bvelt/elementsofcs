package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class MuxBusTest {

  private final List<Pin> inputA = Pin.create16("inputA");
  private final List<Pin> inputB = Pin.create16("inputB");
  private final Pin select = new Pin("select");
  private final List<Pin> output = Pin.create16("output");

  private MuxBus gate = MuxBus.create16(inputA, inputB, select, output);

  // | A | B | Sel | Out |
  private boolean[][] truthTable = new boolean[][] {
      { true, true, true, true },
      { true, true, false, true },
      { true, false, true, true },
      { true, false, false, false },
      { false, true, true, false },
      { false, true, false, true },
      { false, true, true, false },
      { false, true, false, true }
  };

  @Test
  public void outputsShouldMatchExpectedOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {
      boolean inputAValue = truthTable[i][0];
      boolean inputBValue = truthTable[i][1];

      boolean selectBit = truthTable[i][2];

      boolean expectedOutputValue = truthTable[i][3];

      gate.reset();

      inputA.forEach(p -> p.setValue(inputAValue));
      inputB.forEach(p -> p.setValue(inputBValue));

      select.setValue(selectBit);

      gate.eval();

      String msg = String.format("When A is %s, B is %s, and select is %s, expected output is %s",
          inputAValue, inputBValue, selectBit, expectedOutputValue);
      assertTrue(msg, output.stream().allMatch(p -> p.getValue() == expectedOutputValue));
    }
  }

}
