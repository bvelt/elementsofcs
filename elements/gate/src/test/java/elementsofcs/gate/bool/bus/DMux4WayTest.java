package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class DMux4WayTest {

  private final Pin input = new Pin("input");
  private final List<Pin> select = Pin.create2("select");
  private final Pin outputA = new Pin("outputA");
  private final Pin outputB = new Pin("outputB");
  private final Pin outputC = new Pin("outputC");
  private final Pin outputD = new Pin("outputD");

  private DMux4Way gate = new DMux4Way(input, select, outputA, outputB, outputC, outputD);

  // | Input | Sel[2] | OutA | OutB | OutC | OutD |
  private boolean[][] truthTable = new boolean[][] {
      // Sel=00
      { true, false, false, true, false, false, false },
      { false, false, false, false, false, false, false },
      // Sel=01
      { true, false, true, false, true, false, false },
      { false, false, true, false, false, false, false },
      // Sel=10
      { true, true, false, false, false, true, false },
      { false, true, false, false, false, false, false },
      // Sel=11
      { true, true, true, false, false, false, true },
      { false, true, true, false, false, false, false }
  };

  @Test
  public void outputsShouldMatchExpectedOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {
      boolean inputValue = truthTable[i][0];

      boolean select1stBit = truthTable[i][1];
      boolean select2ndBit = truthTable[i][2];

      boolean outputAValue = truthTable[i][3];
      boolean outputBValue = truthTable[i][4];
      boolean outputCValue = truthTable[i][5];
      boolean outputDValue = truthTable[i][6];

      gate.reset();

      input.setValue(inputValue);

      select.get(0).setValue(select1stBit);
      select.get(1).setValue(select2ndBit);

      gate.eval();

      String msg = String.format("When input=%s selector=[%s,%s], expecting output",
          inputValue, select1stBit, select2ndBit);
      assertTrue(msg + "A=" + outputAValue, outputAValue == outputA.getValue());
      assertTrue(msg + "B=" + outputBValue, outputBValue == outputB.getValue());
      assertTrue(msg + "C=" + outputCValue, outputCValue == outputC.getValue());
      assertTrue(msg + "D=" + outputDValue, outputDValue == outputD.getValue());
    }
  }

}
