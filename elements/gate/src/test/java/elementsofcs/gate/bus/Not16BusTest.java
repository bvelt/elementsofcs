package elementsofcs.gate.bus;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Not16BusTest {

  private final Not16Bus bus = new Not16Bus();
  private final boolean[][] truthTable = new boolean[][] { { true, false }, { false, true } };

  @Test
  public void verifyNot16Bus() {
    for (int i = 0; i < truthTable.length; i++) {
      bus.reset();

      boolean inputValue = truthTable[i][0];
      for (int j = 0; j < bus.getInput().length; j++) {
        bus.getInput()[j].setValue(inputValue);
      }

      bus.eval();

      boolean expectedOutputValue = truthTable[i][1];
      for (int j = 0; j < bus.getInput().length; j++) {
        assertTrue("output[" + j + "] expected to be " + expectedOutputValue,
            bus.getOutput()[j].getValue() == expectedOutputValue);
      }
    }
  }

}
