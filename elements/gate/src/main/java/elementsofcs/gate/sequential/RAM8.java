package elementsofcs.gate.sequential;

import java.util.ArrayList;
import java.util.List;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.bus.DMux8Way;
import elementsofcs.gate.bool.bus.Mux8Way16;
import elementsofcs.gate.bool.bus.MuxBus;

public class RAM8 implements SequentialGate, CompositeGate {

  private final List<Pin> input;
  private final List<Pin> address;
  private final Pin load;
  private final List<Pin> output;

  private final List<Pin> registerA = Pin.create16("registerA");
  private final List<Pin> registerB = Pin.create16("registerB");
  private final List<Pin> registerC = Pin.create16("registerC");
  private final List<Pin> registerD = Pin.create16("registerD");
  private final List<Pin> registerE = Pin.create16("registerE");
  private final List<Pin> registerF = Pin.create16("registerF");
  private final List<Pin> registerG = Pin.create16("registerF");
  private final List<Pin> registerH = Pin.create16("registerH");
  private final Mux8Way16 muxRegistersToOutput;

  private final List<Pin> inputNext = Pin.create16("inputNext");
  private final MuxBus muxInAndOutToInNext;

  private final List<DMux8Way> dmuxInputNextToRegister = new ArrayList<DMux8Way>();

  public RAM8(List<Pin> input, List<Pin> address, Pin load, List<Pin> output) {
    super();
    this.input = input;
    this.address = address;
    this.load = load;
    this.output = output;

    muxRegistersToOutput = new Mux8Way16(registerA, registerB, registerC, registerD,
        registerE, registerF, registerG, registerH, address, output);

    muxInAndOutToInNext = MuxBus.create16(input, output, load, inputNext);

    for (int i = 0; i < Pin.SIZE_16; i++) {
      dmuxInputNextToRegister.add(new DMux8Way(inputNext.get(i), address,
          registerA.get(i), registerB.get(i), registerC.get(i), registerD.get(i),
          registerE.get(i), registerF.get(i), registerG.get(i), registerH.get(i)));
    }

  }

  public List<Pin> getInput() {
    return input;
  }

  public List<Pin> getAddress() {
    return address;
  }

  public Pin getLoad() {
    return load;
  }

  public List<Pin> getOutput() {
    return output;
  }

  @Override
  public void eval() {
    muxRegistersToOutput.eval();
    muxInAndOutToInNext.eval();
    dmuxInputNextToRegister.forEach(Gate::eval);
  }

  @Override
  public void reset() {
    muxRegistersToOutput.reset();
    muxInAndOutToInNext.reset();
    dmuxInputNextToRegister.forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "RAM8 [input=" + input + ", address=" + address + ", load=" + load + ", output=" + output + "]";
  }

}
