package elementsofcs.gate.bool;

import elementsofcs.gate.Pin;

/**
 * Gate that evaluates a two-argument boolean predicate
 * 
 * @author brentvelthoen
 *
 */
public interface BinaryPredicateGate extends BooleanGate {
  Pin getInputA();

  Pin getInputB();

  Pin getOutput();
}
