package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.bus.Not16Bus;

public class Not16BusTest {

  private final Not16Bus bus = new Not16Bus(Pin.createList("inputA", 16), Pin.createList("output", 16));
  private final boolean[][] truthTable = new boolean[][] { { true, false }, { false, true } };

  @Test
  public void verifyNot16Bus() {
    for (int i = 0; i < truthTable.length; i++) {
      bus.reset();

      boolean inputValue = truthTable[i][0];
      for (int j = 0; j < bus.getInput().size(); j++) {
        bus.getInput().get(j).setValue(inputValue);
      }

      bus.eval();

      boolean expectedOutputValue = truthTable[i][1];
      for (int j = 0; j < bus.getInput().size(); j++) {
        assertTrue("output[" + j + "] expected to be " + expectedOutputValue,
            bus.getOutput().get(j).getValue() == expectedOutputValue);
      }
    }
  }

}
