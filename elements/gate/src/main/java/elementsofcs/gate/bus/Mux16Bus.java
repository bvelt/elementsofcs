package elementsofcs.gate.bus;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Pin;

/**
 * 16-pin multiplexor that sets output array to either of two input arrays
 * depending on selector
 * 
 * @author brentvelthoen
 *
 */
public class Mux16Bus implements Bus {

  private final List<Pin> inputA;
  private final List<Pin> inputB;
  private final Pin selector;
  private final List<Pin> output;

  public Mux16Bus(List<Pin> inputA, List<Pin> inputB, Pin selector, List<Pin> output) {
    super();
    Objects.requireNonNull(inputA, "inputA");
    Pin.checkListSize(inputA, Pin.SIZE_16, "inputA");
    this.inputA = Collections.unmodifiableList(inputA);
    Objects.requireNonNull(inputB, "inputB");
    Pin.checkListSize(inputB, Pin.SIZE_16, "inputB");
    this.inputB = Collections.unmodifiableList(inputB);
    Objects.requireNonNull(selector, "selector");
    this.selector = selector;
    Objects.requireNonNull(output, "output");
    Pin.checkListSize(output, Pin.SIZE_16, "output");
    this.output = Collections.unmodifiableList(output);
  }

  public List<Pin> getInputA() {
    return inputA;
  }

  public List<Pin> getInputB() {
    return inputB;
  }

  public Pin getSelector() {
    return selector;
  }

  public List<Pin> getOutput() {
    return output;
  }

  @Override
  public void eval() {
    List<Pin> in = selector.getValue() ? inputA : inputB;
    for (int i = 0; i < in.size(); i++) {
      output.get(i).setValue(in.get(i).getValue());
    }
  }

  @Override
  public void reset() {
    inputA.forEach(in -> in.setValue(false));
    inputB.forEach(in -> in.setValue(false));
    selector.setValue(false);
    output.forEach(out -> out.setValue(false));
  }

  @Override
  public String toString() {
    return "Mux16Bus [inputA=" + inputA + ", inputB=" + inputB + ", selector=" + selector + ", output=" + output + "]";
  }

}
