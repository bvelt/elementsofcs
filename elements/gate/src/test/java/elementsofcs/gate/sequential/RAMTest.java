package elementsofcs.gate.sequential;

import static elementsofcs.gate.bool.alu.BinaryNumber.increment;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class RAMTest {

  // 8 X 8 RAM bank
  private final int size = 8;
  private final int width = 8;

  // 3 = log(8, 2)
  private final List<Pin> address = Pin.createList("address", 3);

  private final List<Pin> input = Pin.create8("in");
  private final Pin load = new Pin("load");
  private final List<Pin> output = Pin.create8("out");

  private final RAM ram = new RAM(size, width, address, input, load, output);

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
    for (int i = 0; i < size; i++, increment(address)) {
      // load register at current address
      load.setValue(true);
      for (int j = 0; j < width; j++) {
        input.get(j).setValue(registerValues[i][j]);
      }
      ram.eval();

      // write register to output
      load.setValue(false);
      ram.eval();

      // verify outputs against original register values
      for (int j = 0; j < width; j++) {
        boolean expectedOutputValue = registerValues[i][j];
        boolean actualOutputValue = output.get(j).getValue();
        assertTrue("At row=" + i + ", col=" + j + ", expecting " + expectedOutputValue,
            expectedOutputValue == actualOutputValue);
      }
    }
  }
}
