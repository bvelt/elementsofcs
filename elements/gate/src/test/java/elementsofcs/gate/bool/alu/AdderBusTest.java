package elementsofcs.gate.bool.alu;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.alu.AdderBus;
import elementsofcs.gate.bool.alu.BinaryNumber;

public class AdderBusTest {

  @Test
  public void calculateFirst20Fibonacci() {

    List<Pin> zero = Pin.create16("pin");

    List<Pin> one = Pin.create16("pin");
    one.get(15).setValue(true);

    List<Pin> fibA = null;
    List<Pin> fibB = null;
    List<Pin> fibC = null;

    long expectFibA = 0L;
    long expectFibB = 0L;
    long expectFibC = 0L;

    for (int i = 0; i < 20; i++) {

      fibA = i == 0 ? zero : fibB;
      fibB = i == 0 ? one : fibC;
      fibC = Pin.create16("pin");

      new AdderBus(16, fibA, fibB, fibC).eval();

      long actual = BinaryNumber.unsignedToLong(fibC);

      expectFibA = i == 0 ? 0L : expectFibB;
      expectFibB = i == 0 ? 1L : expectFibC;
      expectFibC = expectFibA + expectFibB;

      assertTrue("At index " + i + " of Fibonacci sequence, expecting "
          + expectFibC + " but was " + actual,
          expectFibC == actual);
    }
  }

}
