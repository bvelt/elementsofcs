package elementsofcs.gate.bool.primitive;

import elementsofcs.gate.Pin;
import elementsofcs.gate.PrimitiveGate;
import elementsofcs.gate.bool.AbstractBinaryPredicateGate;

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

  public NAndPrimitiveGate(Pin inputA, Pin inputB) {
    this(inputA, inputB, new Pin());
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
