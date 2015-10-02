package com.elementsofcs.gate.sequential;

import java.util.ArrayList;
import java.util.List;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.NotCompositeGate;
import elementsofcs.gate.sequential.ClockedGate;

public class DFlipFlop implements ClockedGate {

  private final Pin clockInput;
  private final Pin inputD;
  private final Pin outputQ;
  private final Pin outputNQ;

  private final List<Gate> gates = new ArrayList<Gate>(3);

  public DFlipFlop(Pin clockInput, Pin inputD, Pin outputQ) {
    this(clockInput, inputD, outputQ, new Pin("outputNQ"));
  }

  public DFlipFlop(Pin clockInput, Pin inputD, Pin outputQ, Pin outputNQ) {
    super();
    this.clockInput = clockInput;
    this.inputD = inputD;
    this.outputQ = outputQ;
    this.outputNQ = outputNQ;

    Pin notClockOut = new Pin("notClockOut");
    NotCompositeGate notClockGate = new NotCompositeGate(clockInput, notClockOut);
    gates.add(notClockGate);

    // master buffers D while clockSignal equals false
    Pin masterQOut = new Pin("masterQOut");
    DLatch masterLatch = new DLatch(notClockOut, inputD, masterQOut);
    gates.add(masterLatch);

    // copies latest D from slave to Q on rising edge of clock
    DLatch slaveLatch = new DLatch(clockInput, masterQOut, outputQ, outputNQ);
    gates.add(slaveLatch);
  }

  @Override
  public void eval() {
    gates.forEach(Gate::eval);
  }

  @Override
  public Pin getClockInput() {
    return clockInput;
  }

  public Pin getInputD() {
    return inputD;
  }

  public Pin getOutputNQ() {
    return outputNQ;
  }

  public Pin getOutputQ() {
    return outputQ;
  }

  @Override
  public void reset() {
    gates.forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "DFlipFlop [clockInput=" + clockInput + ", inputD=" + inputD + ", outputQ=" + outputQ + ", outputNQ=" + outputNQ + "]";
  }

}
