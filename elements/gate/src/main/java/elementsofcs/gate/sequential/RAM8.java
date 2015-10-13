package elementsofcs.gate.sequential;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.bus.DMux8Way;
import elementsofcs.gate.bool.bus.Mux8Way16;

/**
 * 128-bit memory composed of 8 rows of 16-bit registers with 3-bit address
 * 
 * @author brentvelthoen
 *
 */
public class RAM8 implements RAM {

  private final Pin clockInput;
  private final List<Pin> input;
  private final List<Pin> address;
  private final Pin load;
  private final List<Pin> output;

  private final DMux8Way dmuxLoad;
  private final List<Register> registers = new ArrayList<Register>(Pin.SIZE_8);
  private final Mux8Way16 muxOutput;

  public RAM8(List<Pin> input, List<Pin> address, Pin load, List<Pin> output) {
    this(new Pin(), input, address, load, output);
  }

  public RAM8(Pin clockInput, List<Pin> input, List<Pin> address, Pin load, List<Pin> output) {
    super();
    this.clockInput = clockInput;
    this.input = input;
    Objects.requireNonNull(address, "address");
    Pin.checkListSize(address, 3, "address");
    this.address = address;
    this.load = load;
    this.output = output;

    // dmux load to register loads
    List<Pin> rload = Pin.create8();
    dmuxLoad = new DMux8Way(load, address,
        rload.get(0), rload.get(1), rload.get(2), rload.get(3),
        rload.get(4), rload.get(5), rload.get(6), rload.get(7));

    // init registers
    for (int i = 0; i < Pin.SIZE_8; i++) {
      List<Pin> rout = Pin.create16();
      registers.add(Register.create16(clockInput, input, rload.get(i), rout));
    }

    // mux register outputs to output
    muxOutput = new Mux8Way16(
        registers.get(0).getOutput(), registers.get(1).getOutput(),
        registers.get(2).getOutput(), registers.get(3).getOutput(),
        registers.get(4).getOutput(), registers.get(5).getOutput(),
        registers.get(6).getOutput(), registers.get(7).getOutput(),
        address, output);
  }

  @Override
  public void eval() {
    dmuxLoad.eval();
    registers.forEach(Gate::eval);
    muxOutput.eval();
  }

  @Override
  public List<Pin> getAddress() {
    return address;
  }

  @Override
  public Pin getClockInput() {
    return clockInput;
  }

  @Override
  public List<Pin> getInput() {
    return input;
  }

  @Override
  public Pin getLoad() {
    return load;
  }

  @Override
  public List<Pin> getOutput() {
    return output;
  }

  @Override
  public int getSize() {
    return Pin.SIZE_8;
  }

  @Override
  public int getWidth() {
    return Pin.SIZE_16;
  }

  @Override
  public void reset() {
    dmuxLoad.reset();
    registers.forEach(Gate::reset);
    muxOutput.reset();
  }

  @Override
  public String toString() {
    return "RAM8 [clockInput=" + clockInput + ", input=" + input + ", address=" + address + ", load=" + load + ", output=" + output + "]";
  }

}
