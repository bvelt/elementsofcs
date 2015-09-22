package elementsofcs.gate.bool.bus;

import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.DMuxCompositeGate;

/**
 * 8-way demultiplexor
 * 
 * <pre>
 * if sel=000 then a=in, b=c=d=e=f=g=h=0
 * else if sel=001 then b=in, a=c=d=e=f=g=h=0
 * else if sel=010 then c=in, a=b=d=e=f=g=h=0
 * else if sel=011 then d=in, a=b=c=e=f=g=h=0
 * else if sel=100 then e=in, a=b=c=d=f=g=h=0
 * else if sel=101 then f=in, a=b=c=d=e=g=h=0
 * else if sel=110 then g=in, a=b=c=d=e=f=h=0
 * else if sel=111 then h=in, a=b=c=d=e=f=g=0
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class DMux8Way implements Bus {

  private final Pin input;

  private final List<Pin> select;

  private final Pin outputA;
  private final Pin outputB;
  private final Pin outputC;
  private final Pin outputD;
  private final Pin outputE;
  private final Pin outputF;
  private final Pin outputG;
  private final Pin outputH;

  private final DMuxCompositeGate dmuxX;
  private final DMux4Way dmuxY;
  private final DMux4Way dmuxZ;

  public DMux8Way(Pin input, List<Pin> select, Pin outputA, Pin outputB, Pin outputC, Pin outputD, Pin outputE, Pin outputF, Pin outputG, Pin outputH) {
    super();
    this.input = input;

    Objects.requireNonNull(select, "select");
    Pin.checkListSize(select, 3, "select");
    this.select = select;

    this.outputA = outputA;
    this.outputB = outputB;
    this.outputC = outputC;
    this.outputD = outputD;
    this.outputE = outputE;
    this.outputF = outputF;
    this.outputG = outputG;
    this.outputH = outputH;

    // if sel=[1]XX then outEFGH=in, outABCD=0 else outEFGH=0, outABCD=in
    Pin outABCD = new Pin("outABCD");
    Pin outEFGH = new Pin("outEFGH");
    dmuxX = new DMuxCompositeGate(input, select.get(0), outEFGH, outABCD);

    List<Pin> selXY = select.subList(1, 3);
    dmuxY = new DMux4Way(outABCD, selXY, outputA, outputB, outputC, outputD);
    dmuxZ = new DMux4Way(outEFGH, selXY, outputE, outputF, outputG, outputH);
  }

  public Pin getInput() {
    return input;
  }

  public List<Pin> getSelect() {
    return select;
  }

  public Pin getOutputA() {
    return outputA;
  }

  public Pin getOutputB() {
    return outputB;
  }

  public Pin getOutputC() {
    return outputC;
  }

  public Pin getOutputD() {
    return outputD;
  }

  public Pin getOutputE() {
    return outputE;
  }

  public Pin getOutputF() {
    return outputF;
  }

  public Pin getOutputG() {
    return outputG;
  }

  public Pin getOutputH() {
    return outputH;
  }

  @Override
  public void eval() {
    dmuxX.eval();
    dmuxY.eval();
    dmuxZ.eval();
  }

  @Override
  public void reset() {
    dmuxX.reset();
    dmuxY.reset();
    dmuxZ.reset();
  }

  @Override
  public String toString() {
    return "DMux8Way [input=" + input + ", select=" + select + ", outputA=" + outputA + ", outputB=" + outputB + ", outputC=" + outputC + ", outputD=" + outputD
        + ", outputE=" + outputE + ", outputF=" + outputF + ", outputG=" + outputG + ", outputH=" + outputH + "]";
  }

}
