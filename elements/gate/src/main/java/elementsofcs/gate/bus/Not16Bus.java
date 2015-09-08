package elementsofcs.gate.bus;

import static elementsofcs.gate.Pin.SIZE_16;

import java.util.List;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.primitive.NotPrimitiveGate;

/**
 * Bus that computes boolean NOT function on each pin of a 16-pin input array
 * 
 * @author brentvelthoen
 *
 */
public class Not16Bus extends AbstractUnaryPredicateBus {

  public Not16Bus(List<Pin> input, List<Pin> output) {
    super(SIZE_16, input, output);
  }

  @Override
  protected Gate createGate(Pin in, Pin out) {
    return new NotPrimitiveGate(in, out);
  }

  @Override
  public String toString() {
    return "Not16Bus [gates=" + gates + "]";
  }

}
