package elementsofcs.gate.bool.alu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.bus.AndBus;
import elementsofcs.gate.bool.bus.Bus;
import elementsofcs.gate.bool.bus.OrBus;
import elementsofcs.gate.bool.bus.OrNWayBus;
import elementsofcs.gate.bool.bus.XOrBus;
import elementsofcs.gate.bool.composite.AndCompositeGate;
import elementsofcs.gate.bool.composite.NotCompositeGate;

/**
 * Arithmetic Logic Unit
 * 
 * <pre>
 * 
 * if zx=1 then x'=0 
 * else if nx=1 then x'=!x
 * else x'=x
 * 
 * if zy=1 then y'=0
 * else if ny=1 then y'=!y
 * else y'=y
 * 
 * if f=1 then out=x + y ('+' = arithmetic addition) 
 * else out=x &amp; y
 * 
 * if no=1 then out'=!out 
 * else out'=out
 * 
 * zr=1 iff out=0
 * ng=1 iff out&lt;0
 * 
 * </pre>
 * 
 * @author brentvelthoen
 *
 */
public class ALU implements Bus {

  // two 16-bit data inputs
  private final List<Pin> x;
  private final List<Pin> y;

  // control bits
  private final Pin zx; // zero x input
  private final Pin nx; // negate x input
  private final Pin zy; // zero y input
  private final Pin ny; // negate y input
  private final Pin f; // function code: 1 for Add, 0 for And
  private final Pin no; // negate output

  // output
  private final List<Pin> out;
  private final Pin zr; // true iff out=0
  private final Pin ng; // true iff out<0

  private final List<Gate> gates = new ArrayList<Gate>();

  public ALU(List<Pin> x, List<Pin> y, Pin zx, Pin nx, Pin zy, Pin ny, Pin f, Pin no, List<Pin> out, Pin zr, Pin ng) {
    super();
    Objects.requireNonNull(x, "x");
    Objects.requireNonNull(y, "y");
    Objects.requireNonNull(zx, "zx");
    Objects.requireNonNull(nx, "nx");
    Objects.requireNonNull(zy, "zy");
    Objects.requireNonNull(ny, "ny");
    Objects.requireNonNull(f, "f");
    Objects.requireNonNull(no, "no");
    Objects.requireNonNull(out, "out");
    Objects.requireNonNull(zr, "zr");
    Objects.requireNonNull(ng, "ng");
    Pin.checkListSize(x, Pin.SIZE_16, "x");
    Pin.checkListSize(y, Pin.SIZE_16, "y");
    Pin.checkListSize(out, Pin.SIZE_16, "out");
    this.x = x;
    this.y = y;
    this.zx = zx;
    this.nx = nx;
    this.zy = zy;
    this.ny = ny;
    this.f = f;
    this.no = no;
    this.out = out;
    this.zr = zr;
    this.ng = ng;

    // zx: enable if zx=1
    initZeroGate(x, zx);
    // nx
    initNegateXGate();

    // zy: enable if zy=1
    initZeroGate(y, zy);
    // ny
    initNegateYGate();

    // f
    initFunctionCodeGate();

    // no
    initNegationGate(out, no);

    // zr
    initOutputEqualsZeroGate();
    // ng
    initOutputLessThanZeroGate();
  }

  private void initNegateYGate() {
    // ny: enable only if zy=0 and ny=1

    // NOT(zy)
    Pin zyDisabledOut = new Pin("zyDisabledOut");
    NotCompositeGate zyDisabledGate = new NotCompositeGate(zy, zyDisabledOut);
    gates.add(zyDisabledGate);

    // AND(NOT(zy), ny)
    Pin nyEnabledOut = new Pin("nyEnabledOut");
    AndCompositeGate nyEnabledGate = new AndCompositeGate(zyDisabledOut, ny, nyEnabledOut);
    gates.add(nyEnabledGate);

    initNegationGate(y, nyEnabledOut);
  }

  private void initNegateXGate() {
    // nx: enable only if zx=0 and nx=1

    // NOT(zx)
    Pin zxDisabledOut = new Pin("zxDisabledOut");
    NotCompositeGate zxDisabledGate = new NotCompositeGate(zx, zxDisabledOut);
    gates.add(zxDisabledGate);

    // AND(NOT(zx), nx)
    Pin nxEnabledOut = new Pin("nxEnabledOut");
    AndCompositeGate nxEnabledGate = new AndCompositeGate(zxDisabledOut, nx, nxEnabledOut);
    gates.add(nxEnabledGate);

    initNegationGate(x, nxEnabledOut);
  }

  /**
   * Gate that implements ng=1 iff out&lt;0
   * 
   * <pre>
   * AND(out[0], out[0])
   * </pre>
   * 
   */
  private void initOutputLessThanZeroGate() {
    // AND(out[0],out[0])
    AndCompositeGate andMSBGate = new AndCompositeGate(out.get(0), out.get(0), ng);
    gates.add(andMSBGate);
  }

  /**
   * Gate that implements zr=1 iff out=0
   * 
   * <pre>
   * NOT(OR(out[0],out[1]..out[15]))
   * </pre>
   * 
   */
  private void initOutputEqualsZeroGate() {
    // OR(out[0],out[1]..out[15])
    Pin orOut = new Pin("orOut");
    OrNWayBus orGate = new OrNWayBus(Pin.SIZE_16, out, orOut);
    gates.add(orGate);

    // NOT(OR(out[0],out[1]..out[15]))
    NotCompositeGate notGate = new NotCompositeGate(orOut, zr);
    gates.add(notGate);
  }

  /**
   * Function gate that does addition or logical and based on f bit
   * 
   * <pre>
   * OR(AND(f, ADD(x, y)), AND(NOT(f), AND(x, y)))
   * </pre>
   * 
   */
  private void initFunctionCodeGate() {
    // ADD(x,y)
    List<Pin> addXYOut = Pin.create16("addXYOut");
    AdderBus addXYGate = AdderBus.create16(x, y, addXYOut);
    gates.add(addXYGate);

    // AND(f, ADD(x,y))
    List<Pin> leftAndOut = Pin.create16("leftAndOut");
    AndBus leftAndGate = AndBus.create16(Pin.fill16(f), addXYOut, leftAndOut);
    gates.add(leftAndGate);

    // NOT(f)
    Pin notFOut = new Pin("notFOut");
    NotCompositeGate notFGate = new NotCompositeGate(f, notFOut);
    gates.add(notFGate);

    // AND(x,y)
    List<Pin> andXYOut = Pin.create16("andXYOut");
    AndBus andXYGate = AndBus.create16(x, y, andXYOut);
    gates.add(andXYGate);

    // AND(NOT(f), AND(x,y))
    List<Pin> rightAndOut = Pin.create16("rightAndOut");
    AndBus rightAndGate = AndBus.create16(Pin.fill16(notFOut), andXYOut, rightAndOut);
    gates.add(rightAndGate);

    // OR(AND(f, ADD(x,y)), AND(NOT(f), AND(x,y)))
    OrBus orGate = OrBus.create16(leftAndOut, rightAndOut, out);
    gates.add(orGate);
  }

  /**
   * Negate input if select=1
   * 
   * <pre>
   * XOR(in, select)
   * </pre>
   * 
   * @param in
   * @param select
   */
  private void initNegationGate(List<Pin> in, Pin select) {
    // XOR(in, select)
    XOrBus negGate = XOrBus.create16(in, Pin.fill16(select), in);
    gates.add(negGate);
  }

  /**
   * Zero input if select=1
   * 
   * <pre>
   * AND(NOT(select), in)
   * </pre>
   * 
   * @param in
   * @param select
   */
  private void initZeroGate(List<Pin> in, Pin select) {
    // NOT(select)
    Pin notSelectOut = new Pin("notSelectOut");
    NotCompositeGate notSelectGate = new NotCompositeGate(select, notSelectOut);
    gates.add(notSelectGate);

    // AND(NOT(select), in)
    AndBus zeroGate = AndBus.create16(in, Pin.fill16(notSelectOut), in);
    gates.add(zeroGate);
  }

  public List<Pin> getX() {
    return x;
  }

  public List<Pin> getY() {
    return y;
  }

  public Pin getZx() {
    return zx;
  }

  public Pin getNx() {
    return nx;
  }

  public Pin getZy() {
    return zy;
  }

  public Pin getNy() {
    return ny;
  }

  public Pin getF() {
    return f;
  }

  public Pin getNo() {
    return no;
  }

  public List<Pin> getOut() {
    return out;
  }

  public Pin getZr() {
    return zr;
  }

  public Pin getNg() {
    return ng;
  }

  @Override
  public void eval() {
    gates.forEach(Gate::eval);
  }

  @Override
  public void reset() {
    gates.forEach(Gate::reset);
  }

  @Override
  public String toString() {
    return "ALU [x=" + x + ", y=" + y + ", zx=" + zx + ", nx=" + nx + ", zy=" + zy + ", ny=" + ny + ", f=" + f + ", no=" + no + ", out=" + out + ", zr=" + zr
        + ", ng=" + ng + "]";
  }

}
