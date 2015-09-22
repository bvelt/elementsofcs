package elementsofcs.gate.bool.bus;

import static elementsofcs.gate.bool.alu.BinaryNumber.increment;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import elementsofcs.gate.Pin;

public class Mux8Way16Test {

  private final List<Pin> inputA = Pin.create16("inputA");
  private final List<Pin> inputB = Pin.create16("inputB");
  private final List<Pin> inputC = Pin.create16("inputC");
  private final List<Pin> inputD = Pin.create16("inputD");
  private final List<Pin> inputE = Pin.create16("inputE");
  private final List<Pin> inputF = Pin.create16("inputF");
  private final List<Pin> inputG = Pin.create16("inputG");
  private final List<Pin> inputH = Pin.create16("inputH");

  private final List<Pin> select = Pin.createList("select", 3);

  private final List<Pin> output = Pin.create16("output");

  private final Mux8Way16 gate = new Mux8Way16(inputA, inputB, inputC, inputD,
      inputE, inputF, inputG, inputH, select, output);

  // | Sel[0] | Sel[1] | Sel[2] |
  private final boolean[][] perms = new boolean[][] {
      { false, false, false },
      { false, false, true },
      { false, true, false },
      { false, true, true },
      { true, false, false },
      { true, false, true },
      { true, true, false },
      { true, true, true }
  };

  private final List<List<Pin>> inputs = new ArrayList<List<Pin>>();

  @Before
  public void initializeInputArrays() {

    inputs.add(inputA);
    inputs.add(inputB);
    inputs.add(inputC);
    inputs.add(inputD);
    inputs.add(inputE);
    inputs.add(inputF);
    inputs.add(inputG);
    inputs.add(inputH);

    // init inputs to sequence of respective select values (i.e.b=001001001..)
    for (int i = 0; i < inputA.size(); i += 4) {
      for (int j = 0; j < inputs.size(); j++) {
        inputs.get(j).get(i).setValue(perms[j][0]);
        inputs.get(j).get(i + 1).setValue(perms[j][1]);
        inputs.get(j).get(i + 2).setValue(perms[j][2]);
        // i + 3 == false
      }
    }
  }

  @Test
  public void verifyPermutations() {
    for (int i = 0; i < inputs.size(); i++, increment(select)) {
      gate.eval();
      for (int j = 0; j < output.size(); j += 4) {
        assertTrue("At select=" + i,
            perms[i][0] == output.get(j).getValue()
                && perms[i][1] == output.get(j + 1).getValue()
                && perms[i][2] == output.get(j + 2).getValue());
      }
    }
  }
}
