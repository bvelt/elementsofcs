package elementsofcs.gate.alu;

import static elementsofcs.gate.Pin.checkListSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bus.Bus;

/**
 * Multibit adder bus composed of array of FullAdder circuits
 * 
 * @author brentvelthoen
 *
 */
public class AdderBus implements Bus {

  public static AdderBus createAdder2(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_2, inputA, inputB, output);
  }

  public static AdderBus createAdder4(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_4, inputA, inputB, output);
  }

  public static AdderBus createAdder8(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_8, inputA, inputB, output);
  }

  public static AdderBus createAdder16(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_16, inputA, inputB, output);
  }

  public static AdderBus createAdder32(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_32, inputA, inputB, output);
  }

  public static AdderBus createAdder64(List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    return new AdderBus(Pin.SIZE_64, inputA, inputB, output);
  }

  protected final int size;
  protected final List<Pin> inputA;
  protected final List<Pin> inputB;
  protected final List<Pin> output;
  protected final List<Gate> gates;

  public AdderBus(int size, List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
    super();
    this.size = size;
    Objects.requireNonNull(inputA, "inputA");
    checkListSize(inputA, size, "inputA");
    Objects.requireNonNull(inputB, "inputB");
    checkListSize(inputB, size, "inputB");
    Objects.requireNonNull(output, "output");
    checkListSize(output, size, "output");
    this.inputA = Collections.unmodifiableList(inputA);
    this.inputB = Collections.unmodifiableList(inputB);
    this.output = Collections.unmodifiableList(output);
    this.gates = Collections.unmodifiableList(createGates());
  }

  private List<Gate> createGates() {
    List<Gate> gs = new ArrayList<Gate>(size);
    Pin outCarry = null;
    for (int i = size - 1; i >= 0; i--) {
      Pin inCarry = outCarry == null ? new Pin("internal") : outCarry;
      Pin inA = inputA.get(i);
      Pin inB = inputB.get(i);
      Pin outSum = output.get(i);
      outCarry = new Pin("internal");
      Gate g = new FullAdderGate(inCarry, inA, inB, outCarry, outSum);
      gs.add(g);
    }
    return gs;
  }

  @Override
  public void eval() {
    gates.forEach(Gate::eval);
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
  public void reset() {
    gates.forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "AdderBus [size=" + size + ", gates=" + gates + "]";
  }

}
