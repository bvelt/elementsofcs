package elementsofcs.gate.bus;

import static elementsofcs.gate.Pin.SIZE_16;

import java.util.List;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.primitive.OrPrimitiveGate;

/**
 * Bus that computes boolean OR function on each pin pair of two 16-pin input
 * arrays
 * 
 * @author brentvelthoen
 *
 */
public class Or16Bus extends AbstractBinaryPredicateBus {

  public Or16Bus(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    super(SIZE_16, inputA, inputB, output);
  }

  @Override
  protected Gate createGate(Pin inA, Pin inB, Pin out) {
    return new OrPrimitiveGate(inA, inB, out);
  }

  @Override
  public String toString() {
    return "Or16Bus [gates=" + gates + "]";
  }

}
