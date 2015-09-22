package elementsofcs.gate.sequential;

import java.util.Objects;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.primitive.MuxPrimitiveGate;

/**
 * Single-bit memory cell
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

  private final Pin inputNext = new Pin("inputNext");
  private final MuxPrimitiveGate muxInAndOutToInNext;

  private final DFF dff;

  public Bit(Pin input, Pin load, Pin output) {
    super();
    Objects.requireNonNull(input, "input");
    Objects.requireNonNull(load, "load");
    Objects.requireNonNull(output, "output");
    this.input = input;
    this.load = load;
    this.output = output;

    muxInAndOutToInNext = new MuxPrimitiveGate(input, output, load, inputNext);

    dff = new DFF(inputNext, output);
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
    muxInAndOutToInNext.eval();
    dff.eval();
  }

  @Override
  public void reset() {
    muxInAndOutToInNext.reset();
    dff.reset();
  }

  @Override
  public String toString() {
    return "Bit [muxInAndOutToInNext=" + muxInAndOutToInNext + ", dff=" + dff + "]";
  }

}
