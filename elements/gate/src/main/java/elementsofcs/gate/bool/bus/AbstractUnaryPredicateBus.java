package elementsofcs.gate.bool.bus;

import static elementsofcs.gate.Pin.checkListSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.UnaryPredicateGate;

/**
 * Bus that computes a one-argument boolean function on each pin of an input
 * array and stores the result in the pin at the same position of an output
 * array
 * 
 * @author brentvelthoen
 *
 */
public abstract class AbstractUnaryPredicateBus implements Bus {

  protected final int size;

  protected final List<Pin> input;
  protected final List<Pin> output;

  protected final List<UnaryPredicateGate> gates = new ArrayList<UnaryPredicateGate>();

  public AbstractUnaryPredicateBus(int size, List<Pin> input, List<Pin> output) {
    super();
    this.size = size;

    Objects.requireNonNull(input, "input");
    checkListSize(input, size, "input");
    this.input = input;

    Objects.requireNonNull(output, "output");
    checkListSize(output, size, "output");
    this.output = output;

    initializeGates();
  }

  protected abstract UnaryPredicateGate createGate(Pin in, Pin out);

  protected void initializeGates() {
    for (int i = 0; i < size; i++) {
      gates.add(createGate(input.get(i), output.get(i)));
    }
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
