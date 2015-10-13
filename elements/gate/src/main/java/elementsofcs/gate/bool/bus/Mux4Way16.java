package elementsofcs.gate.bool.bus;

import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Pin;

/**
 * 4-Way multiplexor with 16-bit input and output arrays
 * 
 * <pre>
 * if sel=00 then out=a
 * else if sel=01 then out=b
 * else if sel=10 then out=c
 * else if sel=11 then out=d
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class Mux4Way16 implements Bus {

  private final List<Pin> inputA;
  private final List<Pin> inputB;
  private final List<Pin> inputC;
  private final List<Pin> inputD;

  private final List<Pin> select;

  private final List<Pin> output;

  private final Mux2Way muxX;
  private final Mux2Way muxY;
  private final Mux2Way muxZ;

  public Mux4Way16(List<Pin> inputA, List<Pin> inputB, List<Pin> inputC, List<Pin> inputD, List<Pin> select, List<Pin> output) {
    super();

    this.inputA = inputA;
    this.inputB = inputB;
    this.inputC = inputC;
    this.inputD = inputD;

    Objects.requireNonNull(select, "select");
    Pin.checkListSize(select, Pin.SIZE_2, "select");
    this.select = select;

    this.output = output;

    // if sel=X1 then out=b else out=a
    muxX = new Mux2Way(Pin.SIZE_16, inputB, inputA, select.get(1));
    // if sel=X1 then out=d else out=c
    muxY = new Mux2Way(Pin.SIZE_16, inputD, inputC, select.get(1));

    // if sel=1X then out=outCD else out=outAB
    muxZ = new Mux2Way(Pin.SIZE_16, muxY.getOutput(), muxX.getOutput(), select.get(0), output);
  }

  public Mux4Way16(List<Pin> inputA, List<Pin> inputB, List<Pin> inputC, List<Pin> inputD, List<Pin> select) {
    this(inputA, inputB, inputC, inputD, select, Pin.create16());
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
    return "Mux4Way16 [inputA=" + inputA + ", inputB=" + inputB + ", inputC=" + inputC + ", inputD=" + inputD + ", select=" + select + ", output=" + output
        + "]";
  }

}
