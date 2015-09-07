package elementsofcs.gate;

public class XOrGate extends PrimitiveGate {

	public XOrGate(Pin inputA, Pin inputB, Pin output) {
		super(inputA, inputB, output);
	}

	@Override
	public void eval() {
		if ((inputA.getValue() && !inputB.getValue()) || (!inputA.getValue() && inputB.getValue())) {
			output.setValue(true);
		} else {
			output.setValue(false);
		}
	}

	@Override
	public String toString() {
		return "XOrGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
	}

}
