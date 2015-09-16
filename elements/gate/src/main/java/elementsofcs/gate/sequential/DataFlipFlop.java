package elementsofcs.gate.sequential;

import elementsofcs.gate.Pin;

/**
 * Data flip-flop with a single input and a single output where out(t) = in(t -
 * 1)
 * 
 * @author brentvelthoen
 *
 */
public interface DataFlipFlop extends SequentialGate {
  Pin getInput();

  Pin getOutput();
}
