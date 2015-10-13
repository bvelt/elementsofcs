package elementsofcs.gate.sequential;

import java.util.ArrayList;
import java.util.List;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.alu.IncrBus;
import elementsofcs.gate.bool.bus.Mux2Way;
import elementsofcs.gate.bool.bus.OrNWayBus;

/**
 * 16-bit counter
 * 
 * <pre>
 * if reset(t)=1 then out(t)=0
 * else if load(t)=1 then out(t)=in(t - 1)
 * else if increment(t) then out(t)=out(t - 1) + 1
 * else out(t) = out(t - 1)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class Counter16 implements ClockedGate, CompositeGate {

  private final Pin clockInput;

  private final List<Pin> input;

  private final Pin increment;
  private final Pin load;
  private final Pin reset;

  private final List<Pin> output;
  private final List<Pin> outputNQ;

  private final List<Gate> gates = new ArrayList<Gate>();

  public Counter16(List<Pin> input, Pin increment, Pin load, Pin reset, List<Pin> output) {
    this(new Pin(), input, increment, load, reset, output, Pin.create16());
  }

  public Counter16(Pin clockInput, List<Pin> input, Pin increment, Pin load, Pin reset, List<Pin> output, List<Pin> outputNQ) {
    super();
    this.clockInput = clockInput;
    this.input = input;
    this.increment = increment;
    this.load = load;
    this.reset = reset;
    this.output = output;
    this.outputNQ = outputNQ;

    // INCR(output)=incrOut
    List<Pin> incrOut = Pin.create16();
    IncrBus incrGate = IncrBus.create16(output, incrOut);
    gates.add(incrGate);

    // MUX(incrOut, input, increment)=muxXOut
    List<Pin> muxXOut = Pin.create16();
    Mux2Way muxXGate = Mux2Way.create16(incrOut, input, increment, muxXOut);
    gates.add(muxXGate);

    // MUX(input, muxXOut, load)=muxYOut
    // load=1 takes precedence over increment=1
    List<Pin> muxYOut = Pin.create16();
    Mux2Way muxYGate = Mux2Way.create16(input, muxXOut, load, muxYOut);
    gates.add(muxYGate);

    // MUX(false, muxYOut, reset)=muxResetOut
    // reset=1 takes precedence over load=1 or increment=1
    List<Pin> muxZOut = Pin.create16();
    Mux2Way muxZGate = Mux2Way.create16(Pin.create16(), muxYOut, reset, muxZOut);
    gates.add(muxZGate);

    // OR(increment, load, reset)=rload
    // load register if any increment, load, or reset is true
    Pin rload = new Pin();
    OrNWayBus rloadGate = new OrNWayBus(3, Pin.createList(increment, load, reset), rload);
    gates.add(rloadGate);

    Register reg = new Register(Pin.SIZE_16, clockInput, muxZOut, rload, output, outputNQ);
    gates.add(reg);
  }

  @Override
  public void eval() {
    gates.forEach(Gate::eval);
  }

  @Override
  public Pin getClockInput() {
    return clockInput;
  }

  public Pin getIncrement() {
    return increment;
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

  public Pin getReset() {
    return reset;
  }

  @Override
  public void reset() {
    gates.forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "Counter16 [input=" + input + ", increment=" + increment + ", load=" + load + ", reset=" + reset + ", output=" + output + "]";
  }

}
