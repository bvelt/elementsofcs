package elementsofcs.gate.bool.bus;

import java.util.List;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BinaryPredicateGate;
import elementsofcs.gate.bool.composite.OrCompositeGate;

/**
 * Bus that computes boolean OR function on each pin pair of two input arrays
 * 
 * @author brentvelthoen
 *
 */
public class OrBus extends AbstractBinaryPredicateBus {

  public static OrBus create8(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new OrBus(Pin.SIZE_8, inputA, inputB, output);
  }

  public static OrBus create16(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new OrBus(Pin.SIZE_16, inputA, inputB, output);
  }

  public static OrBus create32(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new OrBus(Pin.SIZE_32, inputA, inputB, output);
  }

  public static OrBus create64(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new OrBus(Pin.SIZE_64, inputA, inputB, output);
  }

  public OrBus(int size, List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    super(size, inputA, inputB, output);
  }

  @Override
  protected BinaryPredicateGate createGate(Pin inA, Pin inB, Pin out) {
    return new OrCompositeGate(inA, inB, out);
  }

  @Override
  public String toString() {
    return "OrBus [size=" + size + ", gates=" + gates + "]";
  }

}
