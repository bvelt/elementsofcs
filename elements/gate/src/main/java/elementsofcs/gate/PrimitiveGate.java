package elementsofcs.gate;

import java.util.Objects;

public abstract class PrimitiveGate implements Gate {

	protected final Pin inputA;
	protected final Pin inputB;
	protected final Pin output;

	public PrimitiveGate(Pin inputA, Pin inputB, Pin output) {
		super();
		Objects.requireNonNull(inputA, "inputA");
		Objects.requireNonNull(inputB, "inputB");
		Objects.requireNonNull(output, "output");
		this.inputA = inputA;
		this.inputB = inputB;
		this.output = output;
	}

	public Pin getInputA() {
		return inputA;
	}

	public Pin getInputB() {
		return inputB;
	}

	public Pin getOutput() {
		return output;
	}

	@Override
	public void reset() {
		inputA.setValue(false);
		inputB.setValue(false);
		output.setValue(false);
	}
}
