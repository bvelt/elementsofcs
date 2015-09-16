package elementsofcs.gate.sequential;

import java.util.Objects;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.primitive.NOrPrimitiveGate;

/**
 * Set/Reset latch composed of two cross-coupled NOR gates. Set and Reset inputs
 * are active high signals (i.e. set to true to assert).
 * 
 * @author brentvelthoen
 *
 */
public class SRNOrLatch implements SetResetLatch {

  private final Pin inputS;
  private final Pin inputR;
  private final Pin outputQ;

  private final NOrPrimitiveGate norX;
  private final NOrPrimitiveGate norY;

  private final Pin outputNotQ = new Pin("!Q");
  private final Pin outputQNext = new Pin("Q'");
  private final Pin outputNotQNext = new Pin("!Q'");

  public SRNOrLatch(Pin inputS, Pin inputR, Pin outputQ) {
    super();
    Objects.requireNonNull(inputS, "inputS");
    Objects.requireNonNull(inputR, "inptuR");
    Objects.requireNonNull(outputQ, "outputQ");
    this.inputS = inputS;
    this.inputR = inputR;
    this.outputQ = outputQ;

    norX = new NOrPrimitiveGate(inputR, outputNotQ, outputQNext);
    norY = new NOrPrimitiveGate(inputS, outputQ, outputNotQNext);
  }

  @Override
  public Pin getInputS() {
    return inputS;
  }

  @Override
  public Pin getInputR() {
    return inputR;
  }

  @Override
  public Pin getOutputQ() {
    return outputQ;
  }

  @Override
  public void eval() {
    checkInputsNotBothTrue();

    initializeInternalPins();

    evaluateUntilEquilibrium();

    checkOutputsNotEqual();
  }

  private void initializeInternalPins() {
    outputNotQ.setValue(!outputQ.getValue());
    outputQNext.setValue(outputQ.getValue());
    outputNotQNext.setValue(outputNotQ.getValue());
  }

  private void evaluateUntilEquilibrium() {
    do {
      outputQ.setValue(outputQNext.getValue());
      outputNotQ.setValue(outputNotQNext.getValue());
      norX.eval();
      norY.eval();
    } while (outputQ.getValue() != outputQNext.getValue()
        || outputNotQ.getValue() != outputNotQNext.getValue());
  }

  private void checkInputsNotBothTrue() {
    if (inputS.getValue() && inputR.getValue()) {
      throw new IllegalStateException("inputS and inputR must not both both be true");
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
    return "SRNOrLatch [inputS=" + inputS + ", inputR=" + inputR + ", outputQ=" + outputQ + ", outputNotQ=" + outputNotQ + ", outputQNext=" + outputQNext
        + ", outputNotQNext=" + outputNotQNext + "]";
  }

}
