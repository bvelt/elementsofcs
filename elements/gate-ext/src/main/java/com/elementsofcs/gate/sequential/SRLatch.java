package com.elementsofcs.gate.sequential;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.NOrCompositeGate;
import elementsofcs.gate.sequential.SequentialGate;

public class SRLatch implements SequentialGate {

  private final Pin inputS;
  private final Pin inputR;
  private final Pin outputQ;
  private final Pin outputNQ;

  private final NOrCompositeGate n1;
  private final NOrCompositeGate n2;

  public SRLatch(Pin inputS, Pin inputR, Pin outputQ) {
    this(inputS, inputR, outputQ, new Pin("outputNQ"));
  }

  public SRLatch(Pin inputS, Pin inputR, Pin outputQ, Pin outputNQ) {
    super();
    this.inputS = inputS;
    this.inputR = inputR;
    this.outputQ = outputQ;
    this.outputNQ = outputNQ;

    n1 = new NOrCompositeGate(inputR, outputNQ, outputQ);
    n2 = new NOrCompositeGate(inputS, outputQ, outputNQ);
  }

  @Override
  public void eval() {
    // run twice to ensure cross-coupled gates settle
    n1.eval();
    n2.eval();
    n1.eval();
    n2.eval();
  }

  public Pin getInputR() {
    return inputR;
  }

  public Pin getInputS() {
    return inputS;
  }

  public Pin getOutputNQ() {
    return outputNQ;
  }

  public Pin getOutputQ() {
    return outputQ;
  }

  @Override
  public void reset() {
    n1.reset();
    n2.reset();
    // Q is always compliment of NQ
    outputNQ.setValue(!outputQ.getValue());
  }

  @Override
  public String toString() {
    return "SRLatch [inputS=" + inputS + ", inputR=" + inputR + ", outputQ=" + outputQ + ", outputNQ=" + outputNQ + "]";
  }

}
