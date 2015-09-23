package elementsofcs.gate.bool.bus;

import java.util.List;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BinaryPredicateGate;
import elementsofcs.gate.bool.composite.XOrCompositeGate;

/**
 * Bus that computes boolean XOR function on each pin pair of two input arrays
 * 
 * @author brentvelthoen
 *
 */
public class XOrBus extends AbstractBinaryPredicateBus {

  public static XOrBus create8(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new XOrBus(Pin.SIZE_8, inputA, inputB, output);
  }

  public static XOrBus create16(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new XOrBus(Pin.SIZE_16, inputA, inputB, output);
  }

  public static XOrBus create32(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new XOrBus(Pin.SIZE_32, inputA, inputB, output);
  }

  public static XOrBus create64(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new XOrBus(Pin.SIZE_64, inputA, inputB, output);
  }

  public XOrBus(int size, List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    super(size, inputA, inputB, output);
  }

  @Override
  protected BinaryPredicateGate createGate(Pin inA, Pin inB, Pin out) {
    return new XOrCompositeGate(inA, inB, out);
  }

  @Override
  public String toString() {
    return "XOrBus [size=" + size + ", gates=" + gates + "]";
  }

}
