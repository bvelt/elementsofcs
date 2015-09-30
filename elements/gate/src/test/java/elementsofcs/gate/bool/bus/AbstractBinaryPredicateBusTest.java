package elementsofcs.gate.bool.bus;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import elementsofcs.gate.Pin;

public abstract class AbstractBinaryPredicateBusTest {

  protected final List<Pin> inputA = Pin.create16("inputA");
  protected final List<Pin> inputB = Pin.create16("inputB");
  protected final List<Pin> output = Pin.create16("output");

  protected Bus bus;
  protected boolean[][] tt;

  @Before
  public void setUp() {
    bus = createBus();
    tt = createTruthTable();
  }

  protected abstract Bus createBus();

  protected abstract boolean[][] createTruthTable();

  @Test
  public void verifyTruthTable() {
    for (int i = 0; i < tt.length; i++) {
      boolean inputAValue = tt[i][0];
      boolean inputBValue = tt[i][1];

      boolean expectedOutputValue = tt[i][2];

      bus.reset();

      for (int j = 0; j < output.size(); j++) {
        inputA.get(j).setValue(inputAValue);
        inputB.get(j).setValue(inputBValue);
      }

      bus.eval();

      for (int j = 0; j < output.size(); j++) {
        assertTrue("output[" + j + "] expected to be " + expectedOutputValue,
            output.get(j).getValue() == expectedOutputValue);
      }
    }
  }
}
