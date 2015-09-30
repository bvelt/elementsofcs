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

  private final DMux4Way gate = new DMux4Way(input, select, outputA, outputB, outputC, outputD);

  // | Input | Sel[2] | OutA | OutB | OutC | OutD |
  private final boolean[][] tt = new boolean[][] {
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
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputValue = tt[i][0];

      boolean select1stBit = tt[i][1];
      boolean select2ndBit = tt[i][2];

      boolean outputAValue = tt[i][3];
      boolean outputBValue = tt[i][4];
      boolean outputCValue = tt[i][5];
      boolean outputDValue = tt[i][6];

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
