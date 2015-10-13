package elementsofcs.gate.bool.alu;

import java.util.Objects;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BooleanGate;
import elementsofcs.gate.bool.composite.XOrCompositeGate;

/**
 * Gate that performs binary addition on three input pins, setting least
 * significant bit of result to sum pin and most significant bit of result to
 * carry pin
 * 
 * <pre>
 * IN: 
 *   a,b,c
 * OUT:
 *   carry,sum
 * PARTS: 
 *   HalfAdder(in=a,b; carry=carryAB; sum=sumAB)
 *   HalfAdder(in=sumAB,c; carry=carryABC; sum=sum)
 *   XOR(in=carryAB,carryABC; out=carry);
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class FullAdderGate implements BooleanGate, CompositeGate {

  private final Pin inputA;
  private final Pin inputB;
  private final Pin inputC;
  private final Pin carry;
  private final Pin sum;

  private final Gate addABGate;
  private final Gate addABCGate;
  private final Gate carryGate;

  public FullAdderGate(Pin inputA, Pin inputB, Pin inputC, Pin carry, Pin sum) {
    super();
    Objects.requireNonNull(inputA, "inputA");
    Objects.requireNonNull(inputB, "inputB");
    Objects.requireNonNull(inputC, "inputC");
    Objects.requireNonNull(carry, "carry");
    Objects.requireNonNull(sum, "sum");
    this.inputA = inputA;
    this.inputB = inputB;
    this.inputC = inputC;
    this.carry = carry;
    this.sum = sum;

    Pin sumABOut = new Pin();
    Pin carryABOut = new Pin();
    addABGate = new HalfAdderGate(inputA, inputB, carryABOut, sumABOut);

    Pin carryABCOut = new Pin();
    addABCGate = new HalfAdderGate(sumABOut, inputC, carryABCOut, sum);

    carryGate = new XOrCompositeGate(carryABOut, carryABCOut, carry);
  }

  public Pin getInputA() {
    return inputA;
  }

  public Pin getInputB() {
    return inputB;
  }

  public Pin getInputC() {
    return inputC;
  }

  public Pin getCarry() {
    return carry;
  }

  public Pin getSum() {
    return sum;
  }

  @Override
  public void eval() {
    addABGate.eval();
    addABCGate.eval();
    carryGate.eval();
  }

  @Override
  public void reset() {
    addABGate.reset();
    addABCGate.reset();
    carryGate.reset();
  }

  @Override
  public String toString() {
    return "FullAdderGate [inputA=" + inputA + ", inputB=" + inputB + ", inputC=" + inputC + ", carry=" + carry + ", sum=" + sum + "]";
  }

}
