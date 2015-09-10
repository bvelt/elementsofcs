package elementsofcs.gate;

/**
 * Computes a boolean function on one or more input pins and stores the results
 * in one or more output pins
 * 
 * @author brentvelthoen
 *
 */
public interface Gate {
  void eval();

  void reset();
}
