package elementsofcs.gate.composite;

import elementsofcs.gate.AbstractBinaryPredicateGate;
import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;

/**
 * IF/THEN conditional gate composed of NOT and OR intermediate gates.
 * 
 * X->Y = OR(NOT(X),Y)
 * 
 * @author brentvelthoen
 *
 */
public class ConditionalCompositeGate extends AbstractBinaryPredicateGate implements CompositeGate {

  private final OrCompositeGate or;
  private final NotCompositeGate not;

  private final Pin intermediate = new Pin("intermediate");

  public ConditionalCompositeGate(Pin inputA, Pin inputB, Pin output) {
    super(inputA, inputB, output);
    not = new NotCompositeGate(inputA, intermediate);
    or = new OrCompositeGate(intermediate, inputB, output);
  }

  @Override
  public void eval() {
    not.eval();
    or.eval();
  }

  @Override
  public void reset() {
    not.reset();
    or.reset();
  }

  @Override
  public String toString() {
    return "ConditionalCompositeGate [or=" + or + ", not=" + not + "]";
  }

}
