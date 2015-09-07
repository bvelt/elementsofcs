package elementsofcs.gate;

/**
 * Gate that evaluates a two-argument boolean predicate
 * 
 * @author brentvelthoen
 *
 */
public interface BinaryPredicateGate extends Gate {
  Pin getInputA();

  Pin getInputB();

  Pin getOutput();
}
