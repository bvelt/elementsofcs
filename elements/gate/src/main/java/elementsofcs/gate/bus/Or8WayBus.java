package elementsofcs.gate.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.primitive.OrPrimitiveGate;

/**
 * 8-Way OR bus that outputs true if at least one input pin is true, otherwise
 * outputs false
 * 
 * @author brentvelthoen
 *
 */
public class Or8WayBus implements Bus {

  private final List<Pin> input;
  private final Pin output;
  private final List<OrPrimitiveGate> gates;

  public Or8WayBus(List<Pin> input, Pin output) {
    super();
    Objects.requireNonNull(input, "input");
    Pin.checkListSize(input, Pin.SIZE_8, "input");
    this.input = Collections.unmodifiableList(input);
    Objects.requireNonNull(output, "output");
    this.output = output;
    gates = new ArrayList<OrPrimitiveGate>(Pin.SIZE_8 - 1);
    createOrChain();
  }

  private void createOrChain() {
    Pin out = null;
    for (int i = 0; i < Pin.SIZE_8;) {
      Pin inA = i == 0 ? input.get(i++) : out;
      Pin inB = input.get(i++);
      out = i < Pin.SIZE_8 ? new Pin("intermediate") : output;
      gates.add(new OrPrimitiveGate(inA, inB, out));
    }
  }

  public List<Pin> getInput() {
    return input;
  }

  public Pin getOutput() {
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
    return "Or8WayBus [gates=" + gates + "]";
  }

}
