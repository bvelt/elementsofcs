package elementsofcs.gate.primitive;

import elementsofcs.gate.AbstractBinaryPredicateGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;

/**
 * Gate that implements boolean NAND function (i.e. if a=b=T then out=F else
 * out=T)
 * 
 * @author brentvelthoen
 *
 */
public class NAndPrimitiveGate extends AbstractBinaryPredicateGate implements PrimitiveGate {

  public NAndPrimitiveGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  @Override
  public void eval() {
    output.setValue(!(inputA.getValue() && inputB.getValue()));
  }

  @Override
  public String toString() {
    return "NAndPrimitiveGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
