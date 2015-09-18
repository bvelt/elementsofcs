package elementsofcs.gate.sequential;

import static elementsofcs.gate.Pin.checkListSize;
import static elementsofcs.gate.bool.alu.BinaryNumber.maximumUnsignedInt;
import static elementsofcs.gate.bool.alu.BinaryNumber.unsignedToInt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;

public class RAM implements SequentialGate, CompositeGate {

  // the number of registers
  private final int size;
  // the number of bits in each register
  private final int width;

  // position of active register
  private final List<Pin> address;

  // register pins
  private final List<Pin> input;
  private final Pin load;
  private final List<Pin> output;

  private final List<Register> registers = new ArrayList<Register>();

  public RAM(int size, int width, List<Pin> address, List<Pin> input, Pin load, List<Pin> output) {
    super();
    this.size = size;
    this.width = width;

    Objects.requireNonNull(address, "address");
    int maxIndex = size - 1;
    int maxAddressableIndex = maximumUnsignedInt(address);
    if (maxAddressableIndex < maxIndex) {
      throw new IllegalArgumentException("Size of address insufficient to specify " + size + " positions");
    }
    this.address = Collections.unmodifiableList(address);

    Objects.requireNonNull(input, "input");
    checkListSize(input, width, "input");
    this.input = Collections.unmodifiableList(input);

    Objects.requireNonNull(load, "load");
    this.load = load;

    Objects.requireNonNull(output, "output");
    checkListSize(output, width, "output");
    this.output = Collections.unmodifiableList(output);

    for (int i = 0; i < size; i++) {
      registers.add(new Register(width, input, load, output));
    }
  }

  public int getSize() {
    return size;
  }

  public int getWidth() {
    return width;
  }

  public List<Pin> getAddress() {
    return address;
  }

  public List<Pin> getInput() {
    return input;
  }

  public Pin getLoad() {
    return load;
  }

  public List<Pin> getOutput() {
    return output;
  }

  @Override
  public void eval() {
    int index = unsignedToInt(address);
    registers.get(index).eval();
  }

  @Override
  public void reset() {
    address.forEach(addr -> addr.setValue(false));
    registers.forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "RAM [size=" + size + ", width=" + width + ", address=" + address + ", input=" + input + ", load=" + load + ", output=" + output + "]";
  }

}
