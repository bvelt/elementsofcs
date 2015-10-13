package com.elementsofcs.gate.bool.composite;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.CompositeGate;
import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.BooleanGate;
import elementsofcs.gate.bool.composite.MuxCompositeGate;
import elementsofcs.gate.bool.composite.NotCompositeGate;
import elementsofcs.gate.bool.composite.XOrCompositeGate;

/**
 * 3-bit Gray Code incrementer circuit
 * 
 * <pre>
 * 
 * 3-bit sequence
 * 0 0 0
 * 0 0 1
 * 0 1 1
 * 0 1 0
 * 1 1 0
 * 1 1 1
 * 1 0 1
 * 1 0 0
 * 
 * ABC | XYZ
 * 
 * ABC | X  ABC | Y  ABC | Z
 * ==========================
 * 000 | 0  000 | 0  000 | 1
 * 001 | 0  001 | 1  001 | 1
 * 010 | 1  010 | 1  010 | 0
 * 011 | 0  011 | 1  011 | 0
 * 100 | 0  100 | 0  100 | 0
 * 101 | 1  101 | 0  101 | 0
 * 110 | 1  110 | 1  110 | 1
 * 111 | 1  111 | 0  111 | 1
 * 
 * X   AB
 * C \ 00 01 11 10
 * 0    0  1  1  0
 * 1    0  0  1  1
 * 
 * !C*B+C*A
 * X=MUX(A,B,C)
 * 
 * Y   AB
 * C \ 00 01 11 10
 * 0    0  1  1  0
 * 1    1  1  0  0
 * 
 * !C*B+C*!A
 * Y=MUX(!A,B,C)
 * 
 * Z   AB
 * C \ 00 01 11 10
 * 0    1  0  1  0
 * 1    1  0  1  0
 * 
 * A*B+!A*!B
 * !(A^B)
 * Z=NOT(XOR(A,B))
 * 
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class GrayCode3BitIncrGate implements BooleanGate, CompositeGate {

  private final Pin inputA;
  private final Pin inputB;
  private final Pin inputC;

  private final Pin outputX;
  private final Pin outputY;
  private final Pin outputZ;

  private final List<Gate> gates = new ArrayList<Gate>(3);

  public GrayCode3BitIncrGate(Pin inputA, Pin inputB, Pin inputC, Pin outputX, Pin outputY, Pin outputZ) {
    super();
    Objects.requireNonNull(inputA, "inputA");
    Objects.requireNonNull(inputB, "inputB");
    Objects.requireNonNull(inputC, "inputC");
    Objects.requireNonNull(outputX, "outputX");
    Objects.requireNonNull(outputY, "outputY");
    Objects.requireNonNull(outputZ, "outputZ");
    this.inputA = inputA;
    this.inputB = inputB;
    this.inputC = inputC;
    this.outputX = outputX;
    this.outputY = outputY;
    this.outputZ = outputZ;

    MuxCompositeGate xGate = new MuxCompositeGate(inputA, inputB, inputC, outputX);
    gates.add(xGate);

    Pin notAOut = new Pin();
    NotCompositeGate notAGate = new NotCompositeGate(inputA, notAOut);
    gates.add(notAGate);

    MuxCompositeGate yGate = new MuxCompositeGate(notAOut, inputB, inputC, outputY);
    gates.add(yGate);

    Pin xorABOut = new Pin();
    XOrCompositeGate xorABGate = new XOrCompositeGate(inputA, inputB, xorABOut);
    gates.add(xorABGate);

    NotCompositeGate zGate = new NotCompositeGate(xorABOut, outputZ);
    gates.add(zGate);
  }

  @Override
  public void eval() {
    gates.forEach(Gate::eval);
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

  public Pin getOutputX() {
    return outputX;
  }

  public Pin getOutputY() {
    return outputY;
  }

  public Pin getOutputZ() {
    return outputZ;
  }

  @Override
  public void reset() {
    gates.forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "GrayCode3BitIncrGate [inputA=" + inputA + ", inputB=" + inputB + ", inputC=" + inputC + ", outputX=" + outputX + ", outputY=" + outputY
        + ", outputZ=" + outputZ + "]";
  }

}
