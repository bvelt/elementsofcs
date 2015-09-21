package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class DMux4WayTest {

  private final Pin input = new Pin("Input");
  private final List<Pin> select = Pin.create2("Select");
  private final Pin outputA = new Pin("OutputA");
  private final Pin outputB = new Pin("OutputB");
  private final Pin outputC = new Pin("OutputC");
  private final Pin outputD = new Pin("OutputD");

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
      boolean selectorAValue = truthTable[i][1];
      boolean selectorBValue = truthTable[i][2];
      boolean outputAValue = truthTable[i][3];
      boolean outputBValue = truthTable[i][4];
      boolean outputCValue = truthTable[i][5];
      boolean outputDValue = truthTable[i][6];

      gate.reset();

      input.setValue(inputValue);
      select.get(0).setValue(selectorAValue);
      select.get(1).setValue(selectorBValue);

      gate.eval();

      String msg = String.format("When input=%s selector=[%s,%s], expecting outputA=%s outputB=%s outputC=%s outputD=%s",
          inputValue, selectorAValue, selectorBValue, outputAValue, outputBValue, outputCValue, outputDValue);
      assertTrue(msg, outputAValue == outputA.getValue());
      assertTrue(msg, outputBValue == outputB.getValue());
      assertTrue(msg, outputCValue == outputC.getValue());
      assertTrue(msg, outputDValue == outputD.getValue());
    }
  }

}
