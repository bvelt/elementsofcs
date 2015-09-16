package elementsofcs.gate.sequential;

import elementsofcs.gate.Pin;

/**
 * Set/Reset latch used to store a single boolean value in {@code outputQ}.
 * 
 * @author brentvelthoen
 *
 */
public interface SetResetLatch extends SequentialGate {
  Pin getInputS();

  Pin getInputR();

  Pin getOutputQ();
}
