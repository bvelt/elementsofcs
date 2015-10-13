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
 * if load(t) then
 *   out(t) = in(t - 1)
 * else
 *   out(t) = out(t - 1)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class Register implements ClockedGate, CompositeGate {

  public static Register create16(Pin clockInput, List<Pin> input, Pin load, List<Pin> output) {
    return new Register(Pin.SIZE_16, clockInput, input, load, output);
  }

  protected final int size;
  protected final Pin clockInput;
  protected final List<Pin> input;
  protected final Pin load;

  protected final List<Pin> output;
  protected final List<Pin> outputNQ;

  protected final List<ENFlipFlop> bits = new ArrayList<ENFlipFlop>();

  public Register(int size, List<Pin> input, Pin load, List<Pin> output) {
    this(size, new Pin(), input, load, output);
  }

  public Register(int size, Pin clockInput, List<Pin> input, Pin load, List<Pin> output) {
    this(size, clockInput, input, load, output, Pin.createList(size));
  }

  public Register(int size, Pin clockInput, List<Pin> input, Pin load, List<Pin> output, List<Pin> outputNQ) {
    super();
    this.size = size;

    this.clockInput = clockInput;

    Objects.requireNonNull(input, "input");
    checkListSize(input, size, "input");
    this.input = input;

    this.load = load;

    Objects.requireNonNull(output, "output");
    checkListSize(output, size, "output");
    this.output = output;

    Objects.requireNonNull(outputNQ, "outputNQ");
    checkListSize(output, size, "outputNQ");
    this.outputNQ = outputNQ;

    for (int i = 0; i < size; i++) {
      bits.add(new ENFlipFlop(clockInput, input.get(i), load, output.get(i), outputNQ.get(i)));
    }
  }

  @Override
  public void eval() {
    bits.forEach(Gate::eval);
  }

  @Override
  public Pin getClockInput() {
    return clockInput;
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

  List<Pin> getOutputNQ() {
    return outputNQ;
  }

  public int getSize() {
    return size;
  }

  @Override
  public void reset() {
    bits.forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "Register [size=" + size + ", clockInput=" + clockInput + ", input=" + input + ", load=" + load + ", output=" + output + "]";
  }

}
