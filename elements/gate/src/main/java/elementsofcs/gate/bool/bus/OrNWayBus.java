package elementsofcs.gate.bool.bus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.OrGate;

/**
 * N-Way OR bus that outputs true if at least one input pin is true, otherwise
 * outputs false
 * 
 * @author brentvelthoen
 *
 */
public class OrNWayBus implements Bus {

  public static OrNWayBus create8(List<Pin> input, Pin output) {
    return new OrNWayBus(Pin.SIZE_8, input, output);
  }

  private final int size;

  private final List<Pin> input;
  private final Pin output;

  private final List<OrGate> gates;

  public OrNWayBus(int size, List<Pin> input, Pin output) {
    super();
    this.size = size;

    Objects.requireNonNull(input, "input");
    Pin.checkListSize(input, size, "input");
    this.input = input;

    Objects.requireNonNull(output, "output");
    this.output = output;

    gates = new ArrayList<OrGate>(size - 1);
    initializeOrChain();
  }

  private void initializeOrChain() {
    Pin out = null;
    for (int i = 0; i < size;) {
      Pin inA = i == 0 ? input.get(i++) : out;
      Pin inB = input.get(i++);
      out = i < size ? new Pin() : output;
      gates.add(new OrGate(inA, inB, out));
    }
  }

  public List<Pin> getInput() {
    return input;
  }

  public Pin getOutput() {
    return output;
  }

  public int getSize() {
    return size;
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
    return "OrNWayBus [size=" + size + ", gates=" + gates + "]";
  }

}
