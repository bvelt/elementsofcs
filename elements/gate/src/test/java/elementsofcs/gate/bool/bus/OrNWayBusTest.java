package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class OrNWayBusTest {

  private final List<Pin> input = Pin.create8("input");
  private final Pin output = new Pin("output");

  private final OrNWayBus bus = OrNWayBus.create8(input, output);

  // | In[0]..In[7] | Out |
  private final boolean[][] tt = new boolean[][] {
      { true, false, false, false, false, false, false, false, true },
      { false, true, false, false, false, false, false, false, true },
      { false, false, true, false, false, false, false, false, true },
      { false, false, false, true, false, false, false, false, true },
      { false, false, false, false, true, false, false, false, true },
      { false, false, false, false, false, true, false, false, true },
      { false, false, false, false, false, false, true, false, true },
      { false, false, false, false, false, false, false, true, true },
      { false, false, false, false, false, false, false, false, false }
  };

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      bus.reset();

      for (int j = 0; j < (tt[i].length - 1); j++) {
        input.get(j).setValue(tt[i][j]);
      }

      bus.eval();

      boolean expectedOutputValue = tt[i][8];
      assertTrue("At index " + i + ", expecting output " + expectedOutputValue,
          expectedOutputValue == output.getValue());
    }
  }
}
