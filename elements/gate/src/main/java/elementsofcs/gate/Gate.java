package elementsofcs.gate;

/**
 * Computes a function on one or more binary input variables (input pins) and
 * stores the result in one or more binary output variables (output pins).
 * Models a physical digital circuit that manipulates binary signals.
 * 
 * @author brentvelthoen
 *
 */
public interface Gate {
  void eval();

  void reset();
}
