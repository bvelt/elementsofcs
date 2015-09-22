package elementsofcs.gate.bool.alu;

import static elementsofcs.gate.Pin.checkListSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.bus.Bus;

/**
 * Multi-bit adder bus composed of list of FullAdderGate circuits
 * 
 * @author brentvelthoen
 *
 */
public class AdderBus implements Bus {

  public static AdderBus create2(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_2, inputA, inputB, output);
  }

  public static AdderBus create4(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_4, inputA, inputB, output);
  }

  public static AdderBus create8(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_8, inputA, inputB, output);
  }

  public static AdderBus create16(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_16, inputA, inputB, output);
  }

  public static AdderBus create32(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_32, inputA, inputB, output);
  }

  public static AdderBus create64(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_64, inputA, inputB, output);
  }

  private final int size;

  private final List<Pin> inputA;
  private final List<Pin> inputB;
  private final List<Pin> output;

  private final List<FullAdderGate> adders;

  public AdderBus(int size, List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    super();
    this.size = size;
    Objects.requireNonNull(inputA, "inputA");
    checkListSize(inputA, size, "inputA");
    Objects.requireNonNull(inputB, "inputB");
    checkListSize(inputB, size, "inputB");
    Objects.requireNonNull(output, "output");
    checkListSize(output, size, "output");
    this.inputA = inputA;
    this.inputB = inputB;
    this.output = output;

    adders = new ArrayList<FullAdderGate>(size);
    initializeAdders();
  }

  private void initializeAdders() {
    Pin outCarry = null;
    for (int i = size - 1; i >= 0; i--) {
      Pin inCarry = outCarry == null ? new Pin("internal") : outCarry;
      Pin inA = inputA.get(i);
      Pin inB = inputB.get(i);
      Pin outSum = output.get(i);
      outCarry = new Pin("internal");
      adders.add(new FullAdderGate(inCarry, inA, inB, outCarry, outSum));
    }
  }

  public List<Pin> getInputA() {
    return inputA;
  }

  public List<Pin> getInputB() {
    return inputB;
  }

  public List<Pin> getOutput() {
    return output;
  }

  public int getSize() {
    return size;
  }

  @Override
  public void eval() {
    adders.forEach(Gate::eval);
  }

  @Override
  public void reset() {
    adders.forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "AdderBus [size=" + size + ", inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
