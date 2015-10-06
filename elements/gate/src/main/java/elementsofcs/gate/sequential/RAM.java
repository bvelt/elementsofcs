package elementsofcs.gate.sequential;

import java.util.List;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;

/**
 * RAM chip composed of bank (of specified size) of register chips (of specified
 * width). Number of address bits equals log(size, 2). Total number of bits
 * equals size * width.
 * 
 * <pre>
 * out(t) = RAM[address(t)](t)
 * if load(t - 1) then
 *   RAM[address(t - 1)](t) = in(t - 1)
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
