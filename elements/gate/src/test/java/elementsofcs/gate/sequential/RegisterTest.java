package elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class RegisterTest {

  private final List<Pin> input = Pin.create16("in");
  private final List<Pin> output = Pin.create16("out");
  private final Pin load = new Pin("load");

  private final Register register = new Register(Pin.SIZE_16, input, load, output);

  // | In(t - 1) | Out(t - 1) | Load(t - 1) | Out(t) (when Load(t)=0) |
  private final boolean[][] truthTable = new boolean[][] {
      { false, false, false, false },
      { false, false, true, false },
      { false, true, false, true },
      { false, true, true, false },
      { true, false, true, true },
      { true, false, false, false },
      { true, true, true, true },
      { true, true, false, true }
  };

  @Test
  public void verifyOutputsMatchExpectedOutputsInTruthTable() {
    for (int i = 0; i < truthTable.length; i++) {
      boolean inputValue = truthTable[i][0];
      boolean outputValue = truthTable[i][1];
      boolean loadValue = truthTable[i][2];

      boolean expectedOutNextValue = truthTable[i][3];

      register.reset();

      input.forEach(in -> in.setValue(inputValue));
      output.forEach(out -> out.setValue(outputValue));
      load.setValue(loadValue);

      // at time t - 1, store either output or input in dff internal bit
      register.eval();

      // at time t, send dff's internal bit to output
      load.setValue(false);
      register.eval();

      assertTrue("At i=" + i + ", expecting out=" + expectedOutNextValue,
          output.stream().allMatch(out -> expectedOutNextValue == out.getValue()));
    }
  }

}
