package elementsofcs.gate.sequential;

import java.util.Objects;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.primitive.NAndPrimitiveGate;

/**
 * Set/Reset latch composed of two cross-connected NAND gates. Set and Reset
 * inputs are active low signals (i.e. set to false to assert).
 * 
 * @author brentvelthoen
 *
 */
public class SRNAndLatch implements CompositeGate {

  private final Pin inputS;
  private final Pin inputR;
  private final Pin outputQ;

  private NAndPrimitiveGate nandX;
  private NAndPrimitiveGate nandY;

  private final Pin outputNotQ = new Pin("!Q");
  private final Pin outputQNext = new Pin("Q'");
  private final Pin outputNotQNext = new Pin("!Q'");

  public SRNAndLatch(Pin inputS, Pin inputR, Pin outputQ) {
    super();
    Objects.requireNonNull(inputS, "inputS");
    Objects.requireNonNull(inputR, "inptuR");
    Objects.requireNonNull(outputQ, "outputQ");
    this.inputS = inputS;
    this.inputR = inputR;
    this.outputQ = outputQ;

    nandX = new NAndPrimitiveGate(inputS, outputNotQ, outputQNext);
    nandY = new NAndPrimitiveGate(inputR, outputQ, outputNotQNext);
  }

  public Pin getInputS() {
    return inputS;
  }

  public Pin getInputR() {
    return inputR;
  }

  public Pin getOutputQ() {
    return outputQ;
  }

  @Override
  public void eval() {
    checkInputsNotBothFalse();

    initializeInternalPins();

    evaluateUntilEqulibrium();

    checkOutputsNotEqual();
  }

  private void initializeInternalPins() {
    outputNotQ.setValue(!outputQ.getValue());
    outputQNext.setValue(outputQ.getValue());
    outputNotQNext.setValue(outputNotQ.getValue());
  }

  private void evaluateUntilEqulibrium() {
    do {
      outputQ.setValue(outputQNext.getValue());
      outputNotQ.setValue(outputNotQNext.getValue());
      nandX.eval();
      nandY.eval();
    } while (outputQ.getValue() != outputQNext.getValue()
        || outputNotQ.getValue() != outputNotQNext.getValue());
  }

  private void checkInputsNotBothFalse() {
    if (!(inputS.getValue() || inputR.getValue())) {
      throw new IllegalStateException("inputS and inputR must not both both be false");
    }
  }

  private void checkOutputsNotEqual() {
    if (outputQ.getValue() == outputNotQ.getValue()) {
      throw new IllegalStateException("outputNotQ must be complement of outputQ");
    }
  }

  @Override
  public void reset() {
    inputR.setValue(false);
    inputS.setValue(false);
    outputQ.setValue(false);
    outputNotQ.setValue(false);
    outputQNext.setValue(false);
    outputNotQNext.setValue(false);
  }

  @Override
  public String toString() {
    return "SRNAndLatch [inputS=" + inputS + ", inputR=" + inputR + ", outputQ=" + outputQ + ", outputNotQ=" + outputNotQ + ", outputQNext=" + outputQNext
        + ", outputNotQNext=" + outputNotQNext + "]";
  }

}
