package elementsofcs.gate.sequential;

import java.util.ArrayList;
import java.util.List;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.alu.IncrBus;
import elementsofcs.gate.bool.bus.AndBus;
import elementsofcs.gate.bool.bus.OrBus;
import elementsofcs.gate.bool.bus.OrNWayBus;
import elementsofcs.gate.bool.composite.AndCompositeGate;
import elementsofcs.gate.bool.composite.NOrCompositeGate;
import elementsofcs.gate.bool.composite.NotCompositeGate;
import elementsofcs.gate.bool.primitive.NAndPrimitiveGate;

/**
 * 16-bit counter
 * 
 * <pre>
 * if reset(t - 1)=1 then out(t)=0
 * else if load(t - 1)=1 then out(t)=in(t - 1)
 * else if increment(t - 1) then out(t)=out(t - 1) + 1
 * else out(t) = out(t - 1)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class Counter16 implements SequentialGate, CompositeGate {

  private final List<Pin> input;

  private final Pin increment;
  private final Pin load;
  private final Pin reset;

  private final List<Pin> output;

  private final List<Gate> gates = new ArrayList<Gate>();

  public Counter16(List<Pin> input, Pin increment, Pin load, Pin reset, List<Pin> output) {
    super();
    this.input = input;
    this.increment = increment;
    this.load = load;
    this.reset = reset;
    this.output = output;

    // input for register to support reset and increment
    List<Pin> rinput = Pin.create16("rinput");

    // rload: if increment=1 or load=1 or reset=1 then rload=1
    Pin rload = new Pin("rload");
    OrNWayBus rloadGate = new OrNWayBus(3, Pin.createList(increment, load, reset), rload);
    gates.add(rloadGate);

    // load: load input if load=1

    // reset: rinput=(input & 0) if load=0 and reset=1 (ignore increment)

    // NOT(load)
    Pin notLoadOut = new Pin("notLoadOut");
    NotCompositeGate notLoadGate = new NotCompositeGate(load, notLoadOut);
    gates.add(notLoadGate);

    // NAND(NOT(load), reset) (i.e. mask=0 if load=0 and reset=1)
    Pin inputMaskForResetOut = new Pin("inputMaskForResetOut");
    NAndPrimitiveGate inputMaskForResetGate = new NAndPrimitiveGate(notLoadOut, reset, inputMaskForResetOut);
    gates.add(inputMaskForResetGate);

    // AND(NAND(NOT(load), reset), input)
    AndBus applyInputMaskForResetGate = AndBus.create16(Pin.fill16(inputMaskForResetOut), input, rinput);
    gates.add(applyInputMaskForResetGate);

    // increment: rinput=(output + 1) if (load=0 and reset=0 and inc=1)

    // NOR(reset, load)
    Pin notLoadOrResetOut = new Pin("notLoadOrResetOut");
    NOrCompositeGate notLoadOrResetGate = new NOrCompositeGate(load, reset, notLoadOrResetOut);
    gates.add(notLoadOrResetGate);

    // AND(NOR(reset, load), increment)
    Pin incrEnabledOut = new Pin("incrEnabledOut");
    AndCompositeGate incrEnabledGate = new AndCompositeGate(notLoadOrResetOut, increment, incrEnabledOut);
    gates.add(incrEnabledGate);

    // INCR(out)
    List<Pin> incrOut = Pin.create16("incrOut");
    IncrBus incrGate = IncrBus.create16(output, incrOut);
    gates.add(incrGate);

    // AND(AND(NOR(reset, load), increment), INCR(out))
    AndBus zeroIncrOutIfNotIncrEnabledGate = AndBus.create16(Pin.fill16(incrEnabledOut), incrOut, incrOut);
    gates.add(zeroIncrOutIfNotIncrEnabledGate);

    // NOT(AND(NOR(reset, load), increment))
    Pin notIncrEnabledOut = new Pin("notIncrEnabledOut");
    NotCompositeGate notIncrEnabledGate = new NotCompositeGate(incrEnabledOut, notIncrEnabledOut);
    gates.add(notIncrEnabledGate);

    // AND(NOT(AND(NOR(reset, load), increment)), rinput)
    AndBus zeroInputIfIncrEnabledGate = AndBus.create16(Pin.fill16(notIncrEnabledOut), rinput, rinput);
    gates.add(zeroInputIfIncrEnabledGate);

    // OR(incrOut, rinput)
    OrBus orInputsGate = OrBus.create16(incrOut, rinput, rinput);
    gates.add(orInputsGate);

    Register reg = new Register(Pin.SIZE_16, rinput, rload, output);
    gates.add(reg);
  }

  public List<Pin> getInput() {
    return input;
  }

  public Pin getIncrement() {
    return increment;
  }

  public Pin getLoad() {
    return load;
  }

  public Pin getReset() {
    return reset;
  }

  public List<Pin> getOutput() {
    return output;
  }

  @Override
  public void eval() {
    gates.forEach(Gate::eval);
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
