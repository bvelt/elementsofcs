package elementsofcs.gate.bool.bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.MuxCompositeGate;

/**
 * Multi-bit multiplexor that sets output array to either of two input arrays
 * depending on select
 * 
 * @author brentvelthoen
 *
 */
public class Mux2Way implements Bus {

  public static Mux2Way create8(List<Pin> inputA, List<Pin> inputB, Pin selector, List<Pin> output) {
    return new Mux2Way(Pin.SIZE_8, inputA, inputB, selector, output);
  }

  public static Mux2Way create16(List<Pin> inputA, List<Pin> inputB, Pin selector, List<Pin> output) {
    return new Mux2Way(Pin.SIZE_16, inputA, inputB, selector, output);
  }

  public static Mux2Way create32(List<Pin> inputA, List<Pin> inputB, Pin selector, List<Pin> output) {
    return new Mux2Way(Pin.SIZE_32, inputA, inputB, selector, output);
  }

  public static Mux2Way create64(List<Pin> inputA, List<Pin> inputB, Pin selector, List<Pin> output) {
    return new Mux2Way(Pin.SIZE_64, inputA, inputB, selector, output);
  }

  private final int size;

  private final List<Pin> inputA;
  private final List<Pin> inputB;
  private final Pin select;
  private final List<Pin> output;

  private final List<MuxCompositeGate> gates = new ArrayList<MuxCompositeGate>();

  public Mux2Way(int size, List<Pin> inputA, List<Pin> inputB, Pin select, List<Pin> output) {
    super();
    this.size = size;

    Objects.requireNonNull(inputA, "inputA");
    Pin.checkListSize(inputA, size, "inputA");
    this.inputA = inputA;

    Objects.requireNonNull(inputB, "inputB");
    Pin.checkListSize(inputB, size, "inputB");
    this.inputB = inputB;

    Objects.requireNonNull(select, "select");
    this.select = select;

    Objects.requireNonNull(output, "output");
    Pin.checkListSize(output, size, "output");
    this.output = output;

    initializeGates();
  }

  public Mux2Way(int size, List<Pin> inputA, List<Pin> inputB, Pin select) {
    this(size, inputA, inputB, select, Pin.createList(size));
  }

  private void initializeGates() {
    for (int i = 0; i < size; i++) {
      gates.add(new MuxCompositeGate(inputA.get(i), inputB.get(i), select, output.get(i)));
    }
  }

  public List<Pin> getInputA() {
    return inputA;
  }

  public List<Pin> getInputB() {
    return inputB;
  }

  public Pin getSelect() {
    return select;
  }

  public int getSize() {
    return size;
  }

  public List<Pin> getOutput() {
    return output;
  }

  @Override
  public void eval() {
    gates.forEach(Gate::eval);
  }

  @Override
  public void reset() {
    gates.forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "MuxBus [size=" + size + ", inputA=" + inputA + ", inputB=" + inputB + ", select=" + select + ", output=" + output + "]";
  }

}
