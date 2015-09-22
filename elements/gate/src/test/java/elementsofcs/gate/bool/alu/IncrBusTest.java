package elementsofcs.gate.bool.alu;

import static elementsofcs.gate.bool.alu.BinaryNumber.increment;
import static elementsofcs.gate.bool.alu.BinaryNumber.unsignedToInt;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class IncrBusTest {

  private final List<Pin> input = Pin.create8("input");
  private final List<Pin> output = Pin.create8("output");

  private final IncrBus incr = IncrBus.create8(input, output);

  @Test
  public void incrementUntil20() {
    for (int i = 1; i <= 20; i++, increment(input)) {
      incr.eval();
      int actual = unsignedToInt(incr.getOutput());
      assertTrue("At i=" + i + ", expecting " + i + " but was " + actual,
          i == actual);
    }
  }
}
