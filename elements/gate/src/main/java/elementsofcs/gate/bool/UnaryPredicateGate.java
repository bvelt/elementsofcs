package elementsofcs.gate.bool;

import elementsofcs.gate.Pin;

/**
 * Gate that evaluates a one-argument boolean predicate
 * 
 * @author brentvelthoen
 *
 */
public interface UnaryPredicateGate extends BooleanGate {
  Pin getInput();

  Pin getOutput();
}
