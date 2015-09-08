package elementsofcs.gate.bus;

import static elementsofcs.gate.Pin.SIZE_16;

import java.util.List;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.primitive.AndPrimitiveGate;

/**
 * Bus that computes boolean AND function on each pin pair of two 16-pin input
 * arrays
 * 
 * @author brentvelthoen
 *
 */
public class And16Bus extends AbstractBinaryPredicateBus {

  public And16Bus(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    super(SIZE_16, inputA, inputB, output);
  }

  @Override
  protected Gate createGate(Pin inA, Pin inB, Pin out) {
    return new AndPrimitiveGate(inA, inB, out);
  }

  @Override
  public String toString() {
    return "And16Bus [gates=" + gates + "]";
  }

}
