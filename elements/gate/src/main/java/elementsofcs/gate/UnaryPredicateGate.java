package elementsofcs.gate;

/**
 * Gate that evaluates a one-argument boolean predicate
 * 
 * @author brentvelthoen
 *
 */
public interface UnaryPredicateGate extends Gate {
  Pin getInput();

  Pin getOutput();
}
