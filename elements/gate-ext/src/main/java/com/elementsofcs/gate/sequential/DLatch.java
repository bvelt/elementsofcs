package com.elementsofcs.gate.sequential;

import java.util.ArrayList;
import java.util.List;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.AndCompositeGate;
import elementsofcs.gate.bool.composite.NotCompositeGate;
import elementsofcs.gate.sequential.ClockedGate;

public class DLatch implements ClockedGate {

  private final Pin clockInput;
  private final Pin inputD;
  private final Pin outputQ;
  private final Pin outputNQ;

  private final List<Gate> gates = new ArrayList<Gate>(4);

  public DLatch(Pin clockInput, Pin inputD, Pin outputQ) {
    this(clockInput, inputD, outputQ, new Pin("outputNQ"));
  }

  public DLatch(Pin clockInput, Pin inputD, Pin outputQ, Pin outputNQ) {
    super();
    this.clockInput = clockInput;
    this.inputD = inputD;
    this.outputQ = outputQ;
    this.outputNQ = outputNQ;

    Pin notDOut = new Pin("notDOut");
    NotCompositeGate notDGate = new NotCompositeGate(inputD, notDOut);
    gates.add(notDGate);

    Pin inputR = new Pin("inputR");
    AndCompositeGate rGate = new AndCompositeGate(clockInput, notDOut, inputR);
    gates.add(rGate);

    Pin inputS = new Pin("inputS");
    AndCompositeGate sGate = new AndCompositeGate(clockInput, inputD, inputS);
    gates.add(sGate);

    SRLatch srLatch = new SRLatch(inputS, inputR, outputQ, outputNQ);
    gates.add(srLatch);
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
    return "DLatch [inputD=" + inputD + ", clockInput=" + clockInput + ", outputQ=" + outputQ + ", outputNQ=" + outputNQ + "]";
  }

}
