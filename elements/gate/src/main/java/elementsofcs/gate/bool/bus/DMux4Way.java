package elementsofcs.gate.bool.bus;

import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.composite.DMuxCompositeGate;

/**
 * 4-way demultiplexor
 * 
 * <pre>
 * if sel=00 then a=in, b=c=d=0
 * else if sel=01 then b=in, a=c=d=0
 * else if sel=10 then c=in, a=b=d=0
 * else if sel=11 then d=in, a=b=c=0
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class DMux4Way implements Bus {

  private final Pin input;

  private final List<Pin> select;

  private final Pin outputA;
  private final Pin outputB;
  private final Pin outputC;
  private final Pin outputD;

  private final DMuxCompositeGate dmuxX;
  private final DMuxCompositeGate dmuxY;
  private final DMuxCompositeGate dmuxZ;

  public DMux4Way(Pin input, List<Pin> select, Pin outputA, Pin outputB, Pin outputC, Pin outputD) {
    super();
    this.input = input;
    Objects.requireNonNull(select, "select");
    Pin.checkListSize(select, 2, "select");
    this.select = select;
    this.outputA = outputA;
    this.outputB = outputB;
    this.outputC = outputC;
    this.outputD = outputD;

    // if sel=X[1] then outCD=in, outAB=0 else outCD=0, outAB=in
    Pin outAB = new Pin("outAB");
    Pin outCD = new Pin("outCD");
    dmuxX = new DMuxCompositeGate(input, select.get(0), outCD, outAB);

    // if sel=[1]X then outB=outAB, outA=0 else outB=0, outA=outAB
    dmuxY = new DMuxCompositeGate(outAB, select.get(1), outputB, outputA);

    // if sel=[1]X then outD=outCD, outC=0 else outD=0, outC=outCD
    dmuxZ = new DMuxCompositeGate(outCD, select.get(1), outputD, outputC);

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
    return "DMux4Way [input=" + input + ", select=" + select + ", outputA=" + outputA + ", outputB=" + outputB + ", outputC=" + outputC + ", outputD=" + outputD
        + "]";
  }

}
