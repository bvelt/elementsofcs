package elementsofcs.gate;

public class NotBGate extends PrimitiveGate {

	public NotBGate(Pin inputA, Pin inputB, Pin output) {
		super(inputA, inputB, output);
	}

	@Override
	public void eval() {
		output.setValue(!inputB.getValue());
	}

	@Override
	public String toString() {
		return "NotBGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
	}

}
