package elementsofcs.gate.sequential;

import java.util.List;

import elementsofcs.gate.Pin;

public class Register16 extends AbstractRegister {

  public Register16(List<Pin> input, Pin load, List<Pin> output) {
    super(Pin.SIZE_16, input, load, output);
  }

  @Override
  public String toString() {
    return "Register16 [size=" + size + ", input=" + input + ", load=" + load + ", output=" + output + "]";
  }

}
