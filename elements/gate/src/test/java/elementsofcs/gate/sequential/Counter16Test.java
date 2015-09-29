package elementsofcs.gate.sequential;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class Counter16Test {

  private final List<Pin> input = Pin.create16("input");

  private final Pin increment = new Pin("increment");
  private final Pin load = new Pin("load");
  private final Pin reset = new Pin("reset");

  private final List<Pin> output = Pin.create16("output");

  private final Counter16 counter = new Counter16(input, increment, load, reset, output);

  private final boolean[][] tt = new boolean[][] {
      // in[14]|in[15]|incr|load|reset|ou[14]|ou[15]|in[14]'|in[15]'|ou[14]'|ou[15]'
      // inc=0, load=0, reset=0
      { false, false, false, false, false, false, false, false, false, false, false },
      // inc=0, load=0, reset=1
      { false, false, false, false, true, false, false, false, false, false, false },
      { false, true, false, false, true, false, false, false, true, false, false },
      { true, false, false, false, true, false, false, true, false, false, false },
      { true, true, false, false, true, false, false, true, true, false, false },
      // inc=0, load=1, reset=1: reset should take precedence
      { false, false, false, true, true, false, false, false, false, false, false },
      { false, true, false, true, true, false, false, false, true, false, false },
      { true, false, false, true, true, false, false, true, false, false, false },
      { true, true, false, true, true, false, false, true, true, false, false },
      // inc=0, load=1, reset=0
      { false, false, false, true, false, false, false, false, false, false, false },
      { false, true, false, true, false, false, false, false, true, false, true },
      { true, false, false, true, false, false, false, true, false, true, false },
      { true, true, false, true, false, false, false, true, true, true, true },
      // inc=1, load=1, reset=0: load should take precedence
      { false, false, false, true, false, false, false, false, false, false, false },
      { false, true, false, true, false, false, false, false, true, false, true },
      { true, false, false, true, false, false, false, true, false, true, false },
      { true, true, false, true, false, false, false, true, true, true, true },
      // inc=1, load=0, reset=0
      { true, true, true, false, false, false, false, true, true, false, true },
      { true, true, true, false, false, false, true, true, true, true, false },
      { true, true, true, false, false, true, false, true, true, true, true },
      { true, true, true, false, false, true, true, true, true, false, false },
      // inc=1, load=0, reset=1: reset should take precedence
      { false, false, true, false, true, false, false, false, false, false, false },
      { false, true, true, false, true, false, false, false, true, false, false },
      { true, false, true, false, true, false, false, true, false, false, false },
      { true, true, true, false, true, false, false, true, true, false, false },
      // inc=1, load=1, reset=1: reset should take precedence
      { false, false, false, true, true, true, false, false, false, false, false },
      { false, true, false, true, true, true, false, false, true, false, false },
      { true, false, false, true, true, true, false, true, false, false, false },
      { true, true, false, true, true, true, false, true, true, false, false },
  };

  @Test
  public void verifyTT() {
    for (int i = 0; i < tt.length; i++) {
      counter.reset();

      input.get(input.size() - 2).setValue(tt[i][0]);
      input.get(input.size() - 1).setValue(tt[i][1]);

      increment.setValue(tt[i][2]);
      load.setValue(tt[i][3]);
      reset.setValue(tt[i][4]);

      output.get(output.size() - 2).setValue(tt[i][5]);
      output.get(output.size() - 1).setValue(tt[i][6]);

      counter.eval(); // eval at (t - 1)

      increment.setValue(false);
      load.setValue(false);
      reset.setValue(false);

      counter.eval(); // eval at (t)

      // input should be same
      assertTrue("At i=" + i + ", expecting in[14]'=" + tt[i][7],
          input.get(input.size() - 2).getValue() == tt[i][7]);
      assertTrue("At i=" + i + ", expecting in[15]'=" + tt[i][8],
          input.get(input.size() - 1).getValue() == tt[i][8]);

      // verify output
      assertTrue("At i=" + i + ", expecting out[14]'=" + tt[i][9],
          output.get(output.size() - 2).getValue() == tt[i][9]);
      assertTrue("At i=" + i + ", expecting out[15]'=" + tt[i][10],
          output.get(output.size() - 1).getValue() == tt[i][10]);
    }
  }

}
