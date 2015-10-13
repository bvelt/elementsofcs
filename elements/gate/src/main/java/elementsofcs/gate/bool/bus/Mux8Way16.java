package elementsofcs.gate.bool.bus;

import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Pin;

/**
 * 8-Way multiplexor with 16-bit input and output arrays
 * 
 * <pre>
 * if sel=000 then out=a
 * else if sel=001 then out=b
 * else if sel=010 then out=c
 * else if sel=011 then out=d
 * else if sel=100 then out=e
 * else if sel=101 then out=f
 * else if sel=110 then out=g
 * else if sel=111 then out=h
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class Mux8Way16 implements Bus {

  private final List<Pin> inputA;
  private final List<Pin> inputB;
  private final List<Pin> inputC;
  private final List<Pin> inputD;
  private final List<Pin> inputE;
  private final List<Pin> inputF;
  private final List<Pin> inputG;
  private final List<Pin> inputH;

  private final List<Pin> select;

  private final List<Pin> output;

  private final Mux4Way16 muxX;
  private final Mux4Way16 muxY;
  private final Mux2Way muxZ;

  public Mux8Way16(List<Pin> inputA, List<Pin> inputB, List<Pin> inputC, List<Pin> inputD,
      List<Pin> inputE, List<Pin> inputF, List<Pin> inputG, List<Pin> inputH,
      List<Pin> select, List<Pin> output) {
    super();

    this.inputA = inputA;
    this.inputB = inputB;
    this.inputC = inputC;
    this.inputD = inputD;
    this.inputE = inputE;
    this.inputF = inputF;
    this.inputG = inputG;
    this.inputH = inputH;

    Objects.requireNonNull(select, "select");
    Pin.checkListSize(select, 3, "select");
    this.select = select;

    this.output = output;

    List<Pin> selXY = select.subList(1, 3);
    muxX = new Mux4Way16(inputA, inputB, inputC, inputD, selXY);
    muxY = new Mux4Way16(inputE, inputF, inputG, inputH, selXY);

    // if sel=1XX then out=outEFGH else out=outABCD
    muxZ = new Mux2Way(Pin.SIZE_16, muxY.getOutput(), muxX.getOutput(), select.get(0), output);
  }

  public Mux8Way16(List<Pin> inputA, List<Pin> inputB, List<Pin> inputC, List<Pin> inputD,
      List<Pin> inputE, List<Pin> inputF, List<Pin> inputG, List<Pin> inputH,
      List<Pin> select) {
    this(inputA, inputB, inputC, inputD, inputE, inputF, inputG, inputH, select, Pin.create16());
  }

  public List<Pin> getInputA() {
    return inputA;
  }

  public List<Pin> getInputB() {
    return inputB;
  }

  public List<Pin> getInputC() {
    return inputC;
  }

  public List<Pin> getInputD() {
    return inputD;
  }

  public List<Pin> getInputE() {
    return inputE;
  }

  public List<Pin> getInputF() {
    return inputF;
  }

  public List<Pin> getInputG() {
    return inputG;
  }

  public List<Pin> getInputH() {
    return inputH;
  }

  public List<Pin> getSelect() {
    return select;
  }

  public List<Pin> getOutput() {
    return output;
  }

  @Override
  public void eval() {
    muxX.eval();
    muxY.eval();
    muxZ.eval();
  }

  @Override
  public void reset() {
    muxX.reset();
    muxY.reset();
    muxZ.reset();
  }

  @Override
  public String toString() {
    return "Mux8Way16 [inputA=" + inputA + ", inputB=" + inputB + ", inputC=" + inputC + ", inputD=" + inputD + ", inputE=" + inputE + ", inputF=" + inputF
        + ", inputG=" + inputG + ", inputH=" + inputH + ", select=" + select + ", output=" + output + "]";
  }

}
