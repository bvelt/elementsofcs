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
public class NAndGate extends AbstractBinaryPredicateGate implements PrimitiveGate {

  public NAndGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
  }

  public NAndGate(Pin inputA, Pin inputB) {
    this(inputA, inputB, new Pin());
  }

  @Override
  public void eval() {
    output.setValue(!(inputA.getValue() && inputB.getValue()));
  }

  @Override
  public String toString() {
    return "NAndGate [inputA=" + inputA + ", inputB=" + inputB + ", output=" + output + "]";
  }

}
