package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.TruthTables;

public class NotBusTest {

  private final List<Pin> input = Pin.create16();
  private final List<Pin> output = Pin.create16();

  private final NotBus bus = NotBus.create16(input, output);
  private final boolean[][] tt = TruthTables.NOT;

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputValue = tt[i][0];

      boolean expectedOutputValue = tt[i][1];

      bus.reset();

      for (int j = 0; j < bus.getInput().size(); j++) {
        input.get(j).setValue(inputValue);
      }

      bus.eval();

      for (int j = 0; j < bus.getInput().size(); j++) {
        assertTrue("output[" + j + "] expected to be " + expectedOutputValue,
            output.get(j).getValue() == expectedOutputValue);
      }
    }
  }

}
