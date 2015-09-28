package elementsofcs.gate.sequential;

import static elementsofcs.gate.Pin.checkListSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;

/**
 * Mult-bit memory composed of list of bits of specified size
 * 
 * <pre>
 * if load(t - 1) then
 *   out(t) = in(t - 1)
 * else
 *   out(t) = out(t - 1)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class Register implements SequentialGate, CompositeGate {

  protected final int size;

  protected final List<Pin> input;
  protected final Pin load;
  protected final List<Pin> output;

  protected final List<Bit> bits = new ArrayList<Bit>();

  public static Register create16(List<Pin> input, Pin load, List<Pin> output) {
    return new Register(Pin.SIZE_16, input, load, output);
  }

  public Register(int size, List<Pin> input, Pin load, List<Pin> output) {
    super();
    this.size = size;

    Objects.requireNonNull(input, "input");
    checkListSize(input, size, "input");
    this.input = input;

    Objects.requireNonNull(output, "output");
    checkListSize(output, size, "output");
    this.output = output;

    this.load = load;

    for (int i = 0; i < size; i++) {
      bits.add(new Bit(input.get(i), load, output.get(i)));
    }
  }

  public List<Pin> getInput() {
    return input;
  }

  public Pin getLoad() {
    return load;
  }

  public List<Pin> getOutput() {
    return output;
  }

  public int getSize() {
    return size;
  }

  @Override
  public void eval() {
    bits.forEach(Gate::eval);
  }

  @Override
  public void reset() {
    bits.forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "Register [size=" + size + ", input=" + input + ", load=" + load + ", output=" + output + ", bits=" + bits + "]";
  }

}
