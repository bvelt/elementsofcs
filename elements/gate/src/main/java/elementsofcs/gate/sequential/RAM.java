package elementsofcs.gate.sequential;

import java.util.List;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;

/**
 * RAM chip composed of bank (of specified size) of register chips (of specified
 * width). Number of address bits equals log(size, 2). Total number of bits
 * equals size * width. As with register and flip-flop, RAM only updates output
 * on rising edge of clock. While clock signal is false, output remains
 * unchanged.
 * 
 * <pre>
 * if clock(t)=1 and load(t)=1 then
 *   RAM[address(t)](t) = in(t - 1)
 * out(t) = RAM[address(t)](t)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public interface RAM extends ClockedGate, CompositeGate {
  int getSize();

  int getWidth();

  List<Pin> getInput();

  List<Pin> getAddress();

  Pin getLoad();

  List<Pin> getOutput();
}
