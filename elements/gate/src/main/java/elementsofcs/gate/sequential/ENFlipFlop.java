package elementsofcs.gate.sequential;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.MuxCompositeGate;

/**
 * Enabled flip-flop that behaves like a {@link DFlipFlop} while load input is
 * true. When load input is false, latch is opaque: clock is ignored and output
 * Q maintains its old state.
 * 
 * <pre>
 * if clock(t)=1 and load(t)=1 then outputQ(t) = inputD(t - 1)
 * else outputQ(t) = outputQ(t - 1)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class ENFlipFlop implements ClockedGate, CompositeGate {

  private final Pin clockInput;
  private final Pin inputD;
  private final Pin load;
  private final Pin outputQ;
  private final Pin outputNQ;

  private final MuxCompositeGate muxInputGate;
  private final DFlipFlop dff;

  public ENFlipFlop(Pin clockInput, Pin inputD, Pin load, Pin outputQ) {
    this(clockInput, inputD, load, outputQ, new Pin("outputNQ"));
  }

  public ENFlipFlop(Pin clockInput, Pin inputD, Pin load, Pin outputQ, Pin outputNQ) {
    super();
    this.clockInput = clockInput;
    this.inputD = inputD;
    this.load = load;
    this.outputQ = outputQ;
    this.outputNQ = outputNQ;

    Pin muxInputOut = new Pin("muxInputOut");
    muxInputGate = new MuxCompositeGate(inputD, outputQ, load, muxInputOut);

    dff = new DFlipFlop(clockInput, muxInputOut, outputQ, outputNQ);
  }

  @Override
  public void eval() {
    muxInputGate.eval();
    dff.eval();
  }

  @Override
  public Pin getClockInput() {
    return clockInput;
  }

  public Pin getInputD() {
    return inputD;
  }

  public Pin getLoad() {
    return load;
  }

  public Pin getOutputNQ() {
    return outputNQ;
  }

  public Pin getOutputQ() {
    return outputQ;
  }

  @Override
  public void reset() {
    muxInputGate.reset();
    dff.reset();
  }

  @Override
  public String toString() {
    return "ENFlipFlop [clockInput=" + clockInput + ", inputD=" + inputD + ", load=" + load + ", outputQ=" + outputQ + "]";
  }

}
