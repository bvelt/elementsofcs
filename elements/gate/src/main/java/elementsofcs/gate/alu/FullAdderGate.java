package elementsofcs.gate.alu;

import java.util.Objects;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.primitive.XOrPrimitiveGate;

/**
 * Gate that performs binary addition on three input pins, setting least
 * significant bit of result to sum pin and most significant bit of result to
 * carry pin
 * 
 * IN: a,b,c; OUT: carry,sum; PARTS: HADD(in=a,b; carry=carryAB; sum=sumAB),
 * HADD(in=sumAB,c; carry=carryABC; sum=sum); XOR(in=carryAB,carryABC;
 * out=carry);
 * 
 * @author brentvelthoen
 *
 */
public class FullAdderGate implements CompositeGate {

  private final Pin inputA;
  private final Pin inputB;
  private final Pin inputC;
  private final Pin carry;
  private final Pin sum;

  private final Gate adderAB;
  private final Gate adderABC;
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
    adderAB = new HalfAdderGate(inputA, inputB, carryAB, sumAB);

    Pin carryABC = new Pin("carryOfA+B+C");
    adderABC = new HalfAdderGate(sumAB, inputC, carryABC, sum);

    xorCarry = new XOrPrimitiveGate(carryAB, carryABC, carry);
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
    adderAB.eval();
    adderABC.eval();
    xorCarry.eval();
  }

  @Override
  public void reset() {
    adderAB.reset();
    adderABC.reset();
    xorCarry.reset();
  }

  @Override
  public String toString() {
    return "FullAdderGate [adderAB=" + adderAB + ", adderABC=" + adderABC + ", xorCarry=" + xorCarry + "]";
  }

}
