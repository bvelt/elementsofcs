package elementsofcs.gate.bool.bus;

import java.util.List;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.UnaryPredicateGate;
import elementsofcs.gate.bool.composite.NotCompositeGate;

/**
 * Bus that computes boolean NOT function on each pin of an input array
 * 
 * @author brentvelthoen
 *
 */
public class NotBus extends AbstractUnaryPredicateBus {

  public static NotBus create8(List<Pin> input, List<Pin> output) {
    return new NotBus(Pin.SIZE_8, input, output);
  }

  public static NotBus create16(List<Pin> input, List<Pin> output) {
    return new NotBus(Pin.SIZE_16, input, output);
  }

  public static NotBus create32(List<Pin> input, List<Pin> output) {
    return new NotBus(Pin.SIZE_32, input, output);
  }

  public static NotBus create64(List<Pin> input, List<Pin> output) {
    return new NotBus(Pin.SIZE_64, input, output);
  }

  public NotBus(int size, List<Pin> input, List<Pin> output) {
    super(size, input, output);
  }

  @Override
  protected UnaryPredicateGate createGate(Pin in, Pin out) {
    return new NotCompositeGate(in, out);
  }

  @Override
  public String toString() {
    return "NotBus [size=" + size + ", gates=" + gates + "]";
  }

}
