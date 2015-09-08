package elementsofcs.gate.bus;

import java.util.Arrays;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.primitive.NotPrimitiveGate;

public class Not16Bus implements Gate {

  protected static final int SIZE_16 = 16;

  protected final Pin[] input = new Pin[SIZE_16];
  protected final Pin[] output = new Pin[SIZE_16];
  protected final NotPrimitiveGate[] gates = new NotPrimitiveGate[SIZE_16];

  public Not16Bus() {
    initializeArrays();
  }

  protected void initializeArrays() {
    for (int i = 0; i < SIZE_16; i++) {
      input[i] = new Pin("input[" + i + "]");
      output[i] = new Pin("output[" + i + "]");
      gates[i] = new NotPrimitiveGate(input[i], output[i]);
    }
  }

  public Pin[] getInput() {
    return input;
  }

  public Pin[] getOutput() {
    return output;
  }

  @Override
  public void eval() {
    for (Gate gate : gates) {
      gate.eval();
    }
  }

  @Override
  public void reset() {
    for (Gate gate : gates) {
      gate.reset();
    }
  }

  @Override
  public String toString() {
    return "Not16Bus [gates=" + Arrays.toString(gates) + "]";
  }

}
