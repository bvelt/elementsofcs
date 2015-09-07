package elementsofcs.gate;

public class NotAGate extends PrimitiveGate {

	public NotAGate(Pin inputA, Pin inputB, Pin output) {
		super(inputA, inputB, output);
	}

	@Override
	public void eval() {
		output.setValue(!inputA.getValue());
	}

	@Override
	public String toString() {
		return "NotAGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
	}

}
