package elementsofcs.gate.bool.alu;

import java.util.List;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.bus.Bus;

/**
 * Multi-bit adder circuit that increments input by 1
 * 
 * @author brentvelthoen
 *
 */
public class IncrBus implements Bus {

  public static IncrBus create2(List<Pin> input, List<Pin> output) {
    return new IncrBus(Pin.SIZE_2, input, output);
  }

  public static IncrBus create4(List<Pin> input, List<Pin> output) {
    return new IncrBus(Pin.SIZE_4, input, output);
  }

  public static IncrBus create8(List<Pin> input, List<Pin> output) {
    return new IncrBus(Pin.SIZE_8, input, output);
  }

  public static IncrBus create16(List<Pin> input, List<Pin> output) {
    return new IncrBus(Pin.SIZE_16, input, output);
  }

  public static IncrBus create32(List<Pin> input, List<Pin> output) {
    return new IncrBus(Pin.SIZE_32, input, output);
  }

  public static IncrBus create64(List<Pin> input, List<Pin> output) {
    return new IncrBus(Pin.SIZE_64, input, output);
  }

  private final int size;

  private final List<Pin> input;
  private final List<Pin> output;

  private final AdderBus adder;

  public IncrBus(int size, List<Pin> input, List<Pin> output) {
    super();
    this.size = size;
    this.input = input;
    this.output = output;

    adder = new AdderBus(size, input, BinaryNumber.createOne(size), output);
  }

  public List<Pin> getInput() {
    return input;
  }

  public List<Pin> getOutput() {
    return output;
  }

  public int getSize() {
    return size;
  }

  @Override
  public void eval() {
    adder.eval();
  }

  @Override
  public void reset() {
    adder.reset();
  }

  @Override
  public String toString() {
    return "IncrBus [size=" + size + ", input=" + input + ", output=" + output + "]";
  }

}
