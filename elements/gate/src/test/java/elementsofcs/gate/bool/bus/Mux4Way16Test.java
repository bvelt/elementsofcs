package elementsofcs.gate.bool.bus;

import static elementsofcs.gate.bool.alu.BinaryNumber.increment;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import elementsofcs.gate.Pin;

public class Mux4Way16Test {

  private final List<Pin> inputA = Pin.create16();
  private final List<Pin> inputB = Pin.create16();
  private final List<Pin> inputC = Pin.create16();
  private final List<Pin> inputD = Pin.create16();

  private final List<Pin> select = Pin.create2();

  private final List<Pin> output = Pin.create16();

  private final Mux4Way16 gate = new Mux4Way16(inputA, inputB, inputC, inputD, select, output);

  // | Sel[0] | Sel[1] |
  private final boolean[][] perms = new boolean[][] {
      { false, false },
      { false, true },
      { true, false },
      { true, true }
  };

  private final List<List<Pin>> inputs = new ArrayList<List<Pin>>();

  @Before
  public void initializeInputArrays() {

    inputs.add(inputA);
    inputs.add(inputB);
    inputs.add(inputC);
    inputs.add(inputD);

    // init inputs to sequence of respective select values (i.e.b=010101..)
    for (int i = 0; i < inputA.size(); i += 2) {
      for (int j = 0; j < inputs.size(); j++) {
        inputs.get(j).get(i).setValue(perms[j][0]);
        inputs.get(j).get(i + 1).setValue(perms[j][1]);
      }
    }
  }

  @Test
  public void verifyPermutations() {
    for (int i = 0; i < inputs.size(); i++, increment(select)) {
      gate.eval();
      for (int j = 0; j < output.size(); j += 2) {
        assertTrue("At select=" + i,
            perms[i][0] == output.get(j).getValue()
                && perms[i][1] == output.get(j + 1).getValue());
      }
    }
  }
}
