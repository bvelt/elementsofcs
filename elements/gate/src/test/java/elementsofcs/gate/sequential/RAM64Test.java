package elementsofcs.gate.sequential;

import static elementsofcs.gate.bool.alu.BinaryNumber.increment;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class RAM64Test {

  private final int size = 64;
  private final int width = 16;

  private final Pin clockInput = new Pin();

  private final List<Pin> address = Pin.createList(6);

  private final List<Pin> input = Pin.create16();
  private final Pin load = new Pin();
  private final List<Pin> output = Pin.create16();

  private final RAM64 ram = new RAM64(clockInput, input, address, load, output);

  private final boolean[][] registerValues = new boolean[][] {
      { false, false, false, false, false, false, false, false },
      { true, false, true, false, true, false, true, false },
      { false, true, false, true, false, true, false, true },
      { true, true, false, false, true, true, false, false },
      { false, false, true, true, false, false, true, true },
      { false, false, false, true, true, true, false, false },
      { true, true, true, false, false, false, true, true },
      { false, false, false, false, true, true, true, true }
  };

  @Test
  public void loadInputsAndVerifyOutputsForEachRegisterInRAMBank() {
    ram.reset();
    load.setValue(true);
    for (int i = 0; i < size; i++, increment(address)) {
      // load buffer at current address
      clockInput.setValue(false);
      for (int j = 0; j < width; j++) {
        input.get(j).setValue(registerValues[i % 8][j % 8]);
      }
      ram.eval();

      // write buffer to output
      clockInput.setValue(true);
      ram.eval();

      // verify outputs against original register values
      for (int j = 0; j < width; j++) {
        boolean expectedOutputValue = registerValues[i % 8][j % 8];
        boolean actualOutputValue = output.get(j).getValue();
        assertTrue("At row=" + i + ", col=" + j + ", expecting " + expectedOutputValue,
            expectedOutputValue == actualOutputValue);
      }
    }
  }
}
