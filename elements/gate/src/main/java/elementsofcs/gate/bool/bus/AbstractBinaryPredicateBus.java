package elementsofcs.gate.bool.bus;

import static elementsofcs.gate.Pin.checkListSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;

/**
 * Bus that computes a two-argument boolean predicate on each pair of pins at
 * the same position in two input arrays and stores the result in the pin at the
 * same position in the output array
 * 
 * @author brentvelthoen
 *
 */
public abstract class AbstractBinaryPredicateBus implements Bus {

  protected final List<Pin> inputA;
  protected final List<Pin> inputB;
  protected final List<Pin> output;
  protected final List<Gate> gates;
  protected final int size;

  public AbstractBinaryPredicateBus(int size, List<Pin> inputA, List<Pin> inputB, List<Pin> output) {
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

  protected abstract Gate createGate(Pin inA, Pin inB, Pin out);

  private List<Gate> createGates() {
    List<Gate> gs = new ArrayList<Gate>(size);
    for (int i = 0; i < size; i++) {
      Gate g = createGate(inputA.get(i), inputB.get(i), output.get(i));
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
}
