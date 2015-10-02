package elementsofcs.gate.sequential;

import elementsofcs.gate.Pin;

/**
 * Gate that receives alternating digital signals representing the cycles of a
 * digital clock. A clock cycle is the elapsed time between the beginning of the
 * low phase ({@code clockSignal == false}) and the end of the subsequent high
 * phase ({@code clockSignal == true}) and represents a discrete unit of time.
 * The value of the current clock phase is stored in the {@code clockInput} pin
 * and is updated automatically on each clock signal.
 * 
 * @author brentvelthoen
 *
 */
public interface ClockedGate extends SequentialGate {
  /**
   * Pin that stores value of current clock phase to enable implementation of
   * time-based gate behaviors such as synchronization of outputs on rising edge
   * of the clock (i.e. clock phase changes from low to high)
   * 
   * @return
   */
  Pin getClockInput();

  /**
   * Updates clock input pin with clock phase value and triggers re-evaluation
   * of gate logic
   * 
   * @param clockSignal
   *          the value of the current clock phase
   */
  default void onClockSignal(boolean clockSignal) {
    getClockInput().setValue(clockSignal);
    eval();
  }
}