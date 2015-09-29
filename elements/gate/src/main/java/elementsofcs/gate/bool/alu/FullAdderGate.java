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

  private final Gate haddAB;
  private final Gate haddABC;
  private final Gate xorCarry;

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

    Pin sumAB = new Pin("sumOfA+B");
    Pin carryAB = new Pin("carryOfA+B");
    haddAB = new HalfAdderGate(inputA, inputB, carryAB, sumAB);

    Pin carryABC = new Pin("carryOfA+B+C");
    haddABC = new HalfAdderGate(sumAB, inputC, carryABC, sum);

    xorCarry = new XOrCompositeGate(carryAB, carryABC, carry);
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
    haddAB.eval();
    haddABC.eval();
    xorCarry.eval();
  }

  @Override
  public void reset() {
    haddAB.reset();
    haddABC.reset();
    xorCarry.reset();
  }

  @Override
  public String toString() {
    return "FullAdderGate [inputA=" + inputA + ", inputB=" + inputB + ", inputC=" + inputC + ", carry=" + carry + ", sum=" + sum + "]";
  }

}
