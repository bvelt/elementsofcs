package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class DMux8WayTest {

  private final Pin input = new Pin();
  private final List<Pin> select = Pin.createList(3);
  private final Pin outputA = new Pin();
  private final Pin outputB = new Pin();
  private final Pin outputC = new Pin();
  private final Pin outputD = new Pin();
  private final Pin outputE = new Pin();
  private final Pin outputF = new Pin();
  private final Pin outputG = new Pin();
  private final Pin outputH = new Pin();

  private final DMux8Way gate = new DMux8Way(input, select, outputA, outputB, outputC, outputD, outputE, outputF, outputG, outputH);

  // | Input | Sel[3] | OutA | OutB | OutC | OutD | OutE | OutF | OutG | OutH |
  private final boolean[][] tt = new boolean[][] {
      // Sel=000
      { true, false, false, false, true, false, false, false, false, false, false, false },
      { false, false, false, false, false, false, false, false, false, false, false, false },
      // Sel=001
      { true, false, false, true, false, true, false, false, false, false, false, false },
      { false, false, false, true, false, false, false, false, false, false, false, false },
      // Sel=010
      { true, false, true, false, false, false, true, false, false, false, false, false },
      { false, false, true, false, false, false, false, false, false, false, false, false },
      // Sel=011
      { true, false, true, true, false, false, false, true, false, false, false, false },
      { false, false, true, true, false, false, false, false, false, false, false, false },
      // Sel=100
      { true, true, false, false, false, false, false, false, true, false, false, false },
      { false, true, false, false, false, false, false, false, false, false, false, false },
      // Sel=101
      { true, true, false, true, false, false, false, false, false, true, false, false },
      { false, true, false, true, false, false, false, false, false, false, false, false },
      // Sel=110
      { true, true, true, false, false, false, false, false, false, false, true, false },
      { false, true, true, false, false, false, false, false, false, false, false, false },
      // Sel=111
      { true, true, true, true, false, false, false, false, false, false, false, true },
      { false, true, true, true, false, false, false, false, false, false, false, false }
  };

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputValue = tt[i][0];

      boolean select1stBit = tt[i][1];
      boolean select2ndBit = tt[i][2];
      boolean select3rdBit = tt[i][3];

      boolean outputAValue = tt[i][4];
      boolean outputBValue = tt[i][5];
      boolean outputCValue = tt[i][6];
      boolean outputDValue = tt[i][7];
      boolean outputEValue = tt[i][8];
      boolean outputFValue = tt[i][9];
      boolean outputGValue = tt[i][10];
      boolean outputHValue = tt[i][11];

      gate.reset();

      input.setValue(inputValue);

      select.get(0).setValue(select1stBit);
      select.get(1).setValue(select2ndBit);
      select.get(2).setValue(select3rdBit);

      gate.eval();

      String msg = String.format("When input=%s select=[%s,%s,%s], expecting output",
          inputValue, select1stBit, select2ndBit, select3rdBit);
      assertTrue(msg + "A=" + outputAValue, outputAValue == outputA.getValue());
      assertTrue(msg + "B=" + outputBValue, outputBValue == outputB.getValue());
      assertTrue(msg + "C=" + outputCValue, outputCValue == outputC.getValue());
      assertTrue(msg + "D=" + outputDValue, outputDValue == outputD.getValue());
      assertTrue(msg + "E=" + outputEValue, outputEValue == outputE.getValue());
      assertTrue(msg + "F=" + outputFValue, outputFValue == outputF.getValue());
      assertTrue(msg + "G=" + outputGValue, outputGValue == outputG.getValue());
      assertTrue(msg + "H=" + outputHValue, outputHValue == outputH.getValue());
    }
  }

}
