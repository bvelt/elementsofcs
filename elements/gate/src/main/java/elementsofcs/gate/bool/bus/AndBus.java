package elementsofcs.gate.bool.bus;

import java.util.List;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BinaryPredicateGate;
import elementsofcs.gate.bool.composite.AndGate;

/**
 * Bus that computes boolean AND function on each pin pair of two input arrays
 * 
 * @author brentvelthoen
 *
 */
public class AndBus extends AbstractBinaryPredicateBus {

  public static AndBus create8(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AndBus(Pin.SIZE_8, inputA, inputB, output);
  }

  public static AndBus create16(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AndBus(Pin.SIZE_16, inputA, inputB, output);
  }

  public static AndBus create32(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AndBus(Pin.SIZE_32, inputA, inputB, output);
  }

  public static AndBus create64(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AndBus(Pin.SIZE_64, inputA, inputB, output);
  }

  public AndBus(int size, List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    super(size, inputA, inputB, output);
  }

  public AndBus(int size, List<Pin> inputA, List<Pin> inputB) {
    this(size, inputA, inputB, Pin.createList(size));
  }

  @Override
  protected BinaryPredicateGate createGate(Pin inA, Pin inB, Pin out) {
    return new AndGate(inA, inB, out);
  }

  @Override
  public String toString() {
    return "AndBus [size=" + size + ", gates=" + gates + "]";
  }

}
