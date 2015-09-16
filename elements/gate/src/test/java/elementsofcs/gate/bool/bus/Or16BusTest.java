package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.bus.Or16Bus;

public class Or16BusTest {

  private final List<Pin> inputA = Pin.createList("inputA", 16);
  private final List<Pin> inputB = Pin.createList("inputB", 16);
  private final List<Pin> output = Pin.createList("output", 16);

  private final Or16Bus bus = new Or16Bus(inputA, inputB, output);

  private final boolean[][] truthTable = new boolean[][] {
      { true, true, true },
      { true, false, true },
      { false, true, true },
      { false, false, false } };

  @Test
  public void verifyOr16Bus() {
    for (int i = 0; i < truthTable.length; i++) {
      bus.reset();

      boolean inputAValue = truthTable[i][0];
      boolean inputBValue = truthTable[i][1];
      for (int j = 0; j < bus.getSize(); j++) {
        bus.getInputA().get(j).setValue(inputAValue);
        bus.getInputB().get(j).setValue(inputBValue);
      }

      bus.eval();

      boolean expectedOutputValue = truthTable[i][2];
      for (int j = 0; j < bus.getSize(); j++) {
        assertTrue("output[" + j + "] expected to be " + expectedOutputValue,
            bus.getOutput().get(j).getValue() == expectedOutputValue);
      }
    }
  }

}
