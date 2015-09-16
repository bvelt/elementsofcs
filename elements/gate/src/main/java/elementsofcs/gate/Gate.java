package elementsofcs.gate;

/**
 * Computes a function on one or more input pins and stores the results in one
 * or more output pins. Models a digital circuit.
 * 
 * @author brentvelthoen
 *
 */
public interface Gate {
  void eval();

  void reset();
}
