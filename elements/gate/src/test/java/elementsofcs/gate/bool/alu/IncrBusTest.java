package elementsofcs.gate.bool.alu;

import static elementsofcs.gate.bool.alu.BinaryNumber.increment;
import static elementsofcs.gate.bool.alu.BinaryNumber.unsignedToInt;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class IncrBusTest {

  private final List<Pin> input = Pin.create16("input");
  private final List<Pin> output = Pin.create16("output");

  private final IncrBus incr = IncrBus.create16(input, output);

  @Test
  public void incrementUntil20() {
    for (int i = 1; i <= 20; i++, increment(input)) {
      assertTrue(unsignedToInt(input) == (i - 1));
      incr.eval();
      int actual = unsignedToInt(incr.getOutput());
      assertTrue("At i=" + i + ", expecting " + i + " but was " + actual,
          i == actual);
    }
  }
}
