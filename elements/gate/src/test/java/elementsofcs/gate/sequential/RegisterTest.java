package elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class RegisterTest {

  private final Pin clockInput = new Pin();
  private final List<Pin> input = Pin.create16();
  private final List<Pin> output = Pin.create16();
  private final Pin load = new Pin();

  private final Register register = new Register(Pin.SIZE_16, clockInput, input, load, output);

  private final boolean[][] tt = new boolean[][] {
      // Clock | D | Load | Q' |
      { false, false, false, false }, // at reset, Q=false
      { true, false, false, false },
      { false, false, true, false },
      { true, false, true, false },
      { false, true, false, false }, // buffer true
      { true, true, false, false },
      { false, true, true, false },
      { true, false, true, true }, // write true, buffer false
      { true, false, false, true },
      { false, false, true, true },
      { true, false, true, false }, // write false
      { false, true, false, false }, // buffer true
      { true, true, false, false },
      { false, true, true, false },
      { true, true, true, true } // load true
  };

  @Test
  public void verifyTruthTable() {
    register.reset();
    for (int i = 0; i < tt.length; i++) {
      clockInput.setValue(tt[i][0]);
      final boolean inputValue = tt[i][1];
      input.forEach(p -> {
        p.setValue(inputValue);
      });
      load.setValue(tt[i][2]);

      register.eval();

      boolean expectedOutputValue = tt[i][3];
      assertTrue("At i=" + i + ", expecting outputQ=" + expectedOutputValue,
          output.stream().allMatch(p -> p.getValue() == expectedOutputValue));
    }
  }

}
