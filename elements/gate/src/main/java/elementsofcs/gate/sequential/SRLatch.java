package elementsofcs.gate.sequential;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.NOrCompositeGate;

/**
 * Single-bit Set/Reset latch composed of two cross-coupled NOR gates. The S and
 * R inputs (Set and Reset) control the value of output Q, which represents the
 * state of the circuit. Asserting S sets Q to true (and NQ to false). Asserting
 * R resets Q to false (and NQ to true). When both S and R are false, the value
 * of Q does not change (i.e. Q(t) = Q(t - 1)).
 * <p>
 * An {@link IllegalStateException} will be thrown on evaluation when both R and
 * S are true or when Q and NQ are equal.
 * 
 * @author brentvelthoen
 *
 */
public class SRLatch implements SequentialGate, CompositeGate {

  private final Pin inputS;
  private final Pin inputR;
  private final Pin outputQ;
  private final Pin outputNQ;

  private final NOrCompositeGate n1;
  private final NOrCompositeGate n2;

  public SRLatch(Pin inputS, Pin inputR, Pin outputQ) {
    this(inputS, inputR, outputQ, new Pin());
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

  private void checkInputsNotBothTrue() {
    if (inputR.getValue() && inputS.getValue()) {
      throw new IllegalStateException("Inputs S and R cannot both be true");
    }
  }

  private void checkOutputsNotEqual() {
    if (outputQ.getValue() == outputNQ.getValue()) {
      throw new IllegalStateException("Outputs Q and NQ cannot be equal");
    }
  }

  @Override
  public void eval() {
    checkInputsNotBothTrue();
    checkOutputsNotEqual();
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
    // Q is always complement of NQ
    outputNQ.setValue(!outputQ.getValue());
  }

  @Override
  public String toString() {
    return "SRLatch [inputS=" + inputS + ", inputR=" + inputR + ", outputQ=" + outputQ + ", outputNQ=" + outputNQ + "]";
  }

}
