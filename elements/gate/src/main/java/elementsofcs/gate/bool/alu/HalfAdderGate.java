package elementsofcs.gate.bool.alu;

import java.util.Objects;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BooleanGate;
import elementsofcs.gate.bool.primitive.AndPrimitiveGate;
import elementsofcs.gate.bool.primitive.XOrPrimitiveGate;

/**
 * Gate that performs binary addition on two input pins, setting least
 * significant bit of result to sum pin and most significant bit of result to
 * carry pin
 * 
 * <pre>
 * IN: 
 *   a,b
 * OUT: 
 *   carry,sum
 * PARTS:
 *   AND(in=a,b; out=carry)
 *   XOR(in=a,b; out=sum)
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class HalfAdderGate implements BooleanGate, CompositeGate {

  private final Pin inputA;
  private final Pin inputB;
  private final Pin carry;
  private final Pin sum;

  private final AndPrimitiveGate carryGate;
  private final XOrPrimitiveGate sumGate;

  public HalfAdderGate(Pin inputA, Pin inputB, Pin carry, Pin sum) {
    super();
    Objects.requireNonNull(inputA, "inputA");
    Objects.requireNonNull(inputB, "inputB");
    Objects.requireNonNull(carry, "carry");
    Objects.requireNonNull(sum, "sum");
    this.inputA = inputA;
    this.inputB = inputB;
    this.carry = carry;
    this.sum = sum;
    carryGate = new AndPrimitiveGate(inputA, inputB, carry);
    sumGate = new XOrPrimitiveGate(inputA, inputB, sum);
  }

  public Pin getInputA() {
    return inputA;
  }

  public Pin getInputB() {
    return inputB;
  }

  public Pin getSum() {
    return sum;
  }

  public Pin getCarry() {
    return carry;
  }

  @Override
  public void eval() {
    carryGate.eval();
    sumGate.eval();
  }

  @Override
  public void reset() {
    carryGate.reset();
    sumGate.reset();
  }

  @Override
  public String toString() {
    return "HalfAdderGate [carryGate=" + carryGate + ", sumGate=" + sumGate + "]";
  }

}
