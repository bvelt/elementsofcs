package elementsofcs.gate.sequential;

import static elementsofcs.gate.Pin.checkListSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.primitive.PrimitiveStorage;

public class RAM implements SequentialGate, CompositeGate {

  // the number of registers
  private final int size;
  // the number of bits in each register
  private final int width;

  // register pins
  private final List<Pin> input;
  private final Pin load;
  private final List<Pin> output;

  private final PrimitiveStorage<Register> storage;

  public RAM(int size, int width, List<Pin> address, List<Pin> input, Pin load, List<Pin> output) {
    super();
    this.size = size;
    this.width = width;

    Objects.requireNonNull(input, "input");
    checkListSize(input, width, "input");
    this.input = input;

    Objects.requireNonNull(load, "load");
    this.load = load;

    Objects.requireNonNull(output, "output");
    checkListSize(output, width, "output");
    this.output = output;

    List<Register> rs = new ArrayList<Register>();
    for (int i = 0; i < size; i++) {
      rs.add(new Register(width, input, load, output));
    }
    storage = new PrimitiveStorage<>(address, rs);
  }

  public int getSize() {
    return size;
  }

  public int getWidth() {
    return width;
  }

  public List<Pin> getAddress() {
    return storage.getAddress();
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
    storage.getItem().eval();
  }

  @Override
  public void reset() {
    storage.getAddress().forEach(addr -> addr.setValue(false));
    storage.getItems().forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "RAM [size=" + size + ", width=" + width + ", input=" + input + ", load=" + load + ", output=" + output + ", storage=" + storage + "]";
  }

}
