package elementsofcs.gate.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class Or8WayBusTest {

  private final List<Pin> input = Pin.create8("input");
  private final Pin output = new Pin("output");

  private final Or8WayBus bus = new Or8WayBus(input, output);

  private final boolean[][] truthTable = new boolean[][] {
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
  public void verifyOutputsMatchExpectedOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {
      bus.reset();

      for (int j = 0; j < (truthTable[i].length - 1); j++) {
        bus.getInput().get(j).setValue(truthTable[i][j]);
      }
      boolean expectedOutputValue = truthTable[i][8];

      bus.eval();
      assertTrue("At index " + i + ", expecting output " + expectedOutputValue,
          expectedOutputValue == bus.getOutput().getValue());
    }
  }
}
