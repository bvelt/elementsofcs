package elementsofcs.gate.sequential;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.bus.Mux8Way16;
import elementsofcs.gate.bool.composite.DMux8Way;

/**
 * 8,192-bit memory composed of 8 rows of RAM64 (8 x (8 x 16)) chips with 9-bit
 * address
 * 
 * @author brentvelthoen
 *
 */
public class RAM128 implements RAM {

  private final Pin clockInput;
  private final List<Pin> input;
  private final List<Pin> address;
  private final Pin load;
  private final List<Pin> output;

  private final DMux8Way dmuxLoad;
  private final List<RAM64> rams = new ArrayList<RAM64>(Pin.SIZE_8);
  private final Mux8Way16 muxOutput;

  public RAM128(List<Pin> input, List<Pin> address, Pin load, List<Pin> output) {
    this(new Pin(), input, address, load, output);
  }

  public RAM128(Pin clockInput, List<Pin> input, List<Pin> address, Pin load, List<Pin> output) {
    super();
    this.clockInput = clockInput;
    this.input = input;
    Objects.requireNonNull(address, "address");
    this.address = address;
    this.load = load;
    this.output = output;

    // address = xxxyyyyyy where xxx=ram and yyyyyy=register inside ram
    Pin.checkListSize(address, 9, "address");
    List<Pin> ramAddress = address.subList(0, 3);
    List<Pin> regAddress = address.subList(3, 9);

    // dmux load to ram loads
    List<Pin> rload = Pin.create8();
    dmuxLoad = new DMux8Way(load, ramAddress,
        rload.get(0), rload.get(1), rload.get(2), rload.get(3),
        rload.get(4), rload.get(5), rload.get(6), rload.get(7));

    // init rams
    for (int i = 0; i < Pin.SIZE_8; i++) {
      List<Pin> rout = Pin.create16();
      rams.add(new RAM64(clockInput, input, regAddress, rload.get(i), rout));
    }

    // mux ram outputs to output
    muxOutput = new Mux8Way16(
        rams.get(0).getOutput(), rams.get(1).getOutput(),
        rams.get(2).getOutput(), rams.get(3).getOutput(),
        rams.get(4).getOutput(), rams.get(5).getOutput(),
        rams.get(6).getOutput(), rams.get(7).getOutput(),
        ramAddress, output);
  }

  @Override
  public void eval() {
    dmuxLoad.eval();
    rams.forEach(Gate::eval);
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
    return Pin.SIZE_128;
  }

  @Override
  public int getWidth() {
    return Pin.SIZE_16;
  }

  @Override
  public void reset() {
    dmuxLoad.reset();
    rams.forEach(Gate::reset);
    muxOutput.reset();
  }

  @Override
  public String toString() {
    return "RAM128 [input=" + input + ", address=" + address + ", load=" + load + ", output=" + output + "]";
  }

}
