package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class NotBusTest {

  private final List<Pin> input = Pin.create16("input");
  private final List<Pin> output = Pin.create16("output");

  private final NotBus bus = NotBus.create16(input, output);

  // | In | Out |
  private final boolean[][] truthTable = new boolean[][] {
      { true, false },
      { false, true } };

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
