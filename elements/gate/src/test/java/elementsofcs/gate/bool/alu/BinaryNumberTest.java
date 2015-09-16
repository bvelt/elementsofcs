package elementsofcs.gate.bool.alu;

import static elementsofcs.gate.bool.alu.BinaryNumber.increment;
import static elementsofcs.gate.bool.alu.BinaryNumber.maximumUnsignedValue;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.alu.BinaryNumber;

public class BinaryNumberTest {

  // MAX UNSIGNED VALUE 65535 (2^16)
  private final List<Pin> input = Pin.create16("in");

  @Test
  public void computeUnsignedLongUpToMaxValueOfInputArray() {
    for (long expect = 0L, max = maximumUnsignedValue(input); expect < max; expect++, increment(input)) {
      long actual = BinaryNumber.unsignedToLong(input);
      assertTrue("Expected " + expect + " but was " + actual, expect == actual);
    }
  }

}
