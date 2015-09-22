package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class AndBusTest {

  private final List<Pin> inputA = Pin.create16("inputA");
  private final List<Pin> inputB = Pin.create16("inputB");
  private final List<Pin> output = Pin.create16("output");

  private final AndBus bus = AndBus.create16(inputA, inputB, output);

  // | A | B | Out |
  private final boolean[][] truthTable = new boolean[][] {
      { true, true, true },
      { true, false, false },
      { false, true, false },
      { false, false, false } };

  @Test
  public void verifyAnd16Bus() {
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
