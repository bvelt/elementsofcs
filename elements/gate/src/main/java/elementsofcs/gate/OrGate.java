package elementsofcs.gate;

public class OrGate extends PrimitiveGate {

	public OrGate(Pin inputA, Pin inputB, Pin output) {
		super(inputA, inputB, output);
	}

	@Override
	public void eval() {
		if (inputA.getValue() || inputB.getValue()) {
			output.setValue(true);
		} else {
			output.setValue(false);
		}
	}

	@Override
	public String toString() {
		return "OrGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
	}

}
