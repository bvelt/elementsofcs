package elementsofcs.gate.bool.bus;

import static elementsofcs.gate.Pin.checkListSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;

/**
 * Bus that computes a one-argument boolean function on each pin of an input
 * array and stores the result in the pin at the same position of an output
 * array
 * 
 * @author brentvelthoen
 *
 */
public abstract class AbstractUnaryPredicateBus implements Bus {

  protected final List<Pin> input;
  protected final List<Pin> output;
  protected final List<Gate> gates;
  protected final int size;

  public AbstractUnaryPredicateBus(int size, List<Pin> input, List<Pin> output) {
    super();
    this.size = size;
    Objects.requireNonNull(input, "input");
    checkListSize(input, size, "input");
    Objects.requireNonNull(output, "output");
    checkListSize(output, size, "output");
    this.input = Collections.unmodifiableList(input);
    this.output = Collections.unmodifiableList(output);
    this.gates = Collections.unmodifiableList(createGates());
  }

  protected abstract Gate createGate(Pin in, Pin out);

  private List<Gate> createGates() {
    List<Gate> gs = new ArrayList<Gate>(size);
    for (int i = 0; i < size; i++) {
      Gate g = createGate(input.get(i), output.get(i));
      gs.add(g);
    }
    return gs;
  }

  @Override
  public void eval() {
    gates.forEach(Gate::eval);
  }

  public List<Pin> getInput() {
    return input;
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
}
