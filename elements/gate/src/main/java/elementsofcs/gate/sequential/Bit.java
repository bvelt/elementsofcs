package elementsofcs.gate.sequential;

import java.util.Objects;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.primitive.MultiplexorPrimitiveGate;

/**
 * Single-bit register
 * 
 * Specification:
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
public class Bit implements SequentialGate, CompositeGate {

  private final Pin input;
  private final Pin load;
  private final Pin output;

  private final Pin outputMux = new Pin("outputMux");

  private final MultiplexorPrimitiveGate mux;
  private final DataFlipFlop dff;

  public Bit(Pin input, Pin load, Pin output) {
    super();
    Objects.requireNonNull(input, "input");
    Objects.requireNonNull(load, "load");
    Objects.requireNonNull(output, "output");
    this.input = input;
    this.load = load;
    this.output = output;

    mux = new MultiplexorPrimitiveGate(input, output, load, outputMux);
    dff = new DataFlipFlop(outputMux, output);
  }

  public Pin getInput() {
    return input;
  }

  public Pin getLoad() {
    return load;
  }

  public Pin getOutput() {
    return output;
  }

  @Override
  public void eval() {
    mux.eval();
    dff.eval();
  }

  @Override
  public void reset() {
    mux.reset();
    dff.reset();
  }

  @Override
  public String toString() {
    return "Bit [mux=" + mux + ", dff=" + dff + "]";
  }

}
