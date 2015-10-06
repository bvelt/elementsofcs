package elementsofcs.gate.sequential;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.AndCompositeGate;

/**
 * Enabled flip-flop that behaves like a {@link DFlipFlop} while load input is
 * true. When load input is false, latch is opaque: clock is ignored and output
 * Q maintains its old state.
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

  private final AndCompositeGate enableGate;
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

    Pin enableOut = new Pin("enableOut");
    enableGate = new AndCompositeGate(clockInput, load, enableOut);

    dff = new DFlipFlop(enableOut, inputD, outputQ, outputNQ);
  }

  @Override
  public void eval() {
    enableGate.eval();
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
    enableGate.reset();
    dff.reset();
  }

  @Override
  public String toString() {
    return "ENFlipFlop [clockInput=" + clockInput + ", inputD=" + inputD + ", load=" + load + ", outputQ=" + outputQ + "]";
  }

}
