package elementsofcs.gate.bool.alu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Gate;
import elementsofcs.gate.Pin;
import elementsofcs.gate.bool.bus.AndBus;
import elementsofcs.gate.bool.bus.Bus;
import elementsofcs.gate.bool.bus.MuxBus;
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

    // zx, nx
    initZeroOrNegateGate(x, zx, nx);
    // zy, ny
    initZeroOrNegateGate(y, zy, ny);

    // f
    initFunctionCodeGate();

    // no
    initNegationGate(out, no);

    // zr
    initOutputEqualsZeroGate();
    // ng
    initOutputLessThanZeroGate();
  }

  /**
   * Initialize gates to handle zero-ing or negation of input array
   * 
   * <pre>
   * | zx | nx | x | x' |
   *    0    0   0   0
   *    0    0   1   1
   *    0    1   0   1
   *    0    1   1   0
   *    1    0   0   0
   *    1    0   1   0
   *    1    1   0   0
   *    1    1   1   0
   *    
   *    !zx*!nx*x+!zx*nx*!x
   *    !zx*(!nx*x)+!zx*(nx*!x)
   *    !zx*(!nx*x+nx*!x)
   *    !zx*(nx^x)
   *    
   *    x' zx:nx
   *    x\ 00 01 11 10
   *    0   0  1  0  0
   *    1   1  0  0  0
   *    
   *    !zx*nx*!x+!zx*!nx*x
   * </pre>
   * 
   * @param in
   *          input array
   * @param zin
   *          control bit for zero-ing (falsifying) input
   * @param nin
   *          control bit for negating input
   * 
   */
  private void initZeroOrNegateGate(List<Pin> in, Pin zin, Pin nin) {
    // XOR(nx,x)
    List<Pin> nxXOrXOut = Pin.create16("nxXOrXOut");
    XOrBus nxXOrXGate = XOrBus.create16(Pin.fill16(nin), in, nxXOrXOut);
    gates.add(nxXOrXGate);
    // NOT(zx)
    Pin notZxOut = new Pin("notZxOut");
    NotCompositeGate notZxGate = new NotCompositeGate(zin, notZxOut);
    gates.add(notZxGate);
    // AND(NOT(zx), XOR(nx,x))
    AndBus zxAndGate = AndBus.create16(Pin.fill16(notZxOut), nxXOrXOut, in);
    gates.add(zxAndGate);
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
   * MUX(ADD(X, Y), AND(X, Y), F, OUT)
   * </pre>
   * 
   */
  private void initFunctionCodeGate() {
    // ADD(x,y)
    List<Pin> addXYOut = Pin.create16("addXYOut");
    AdderBus addXYGate = AdderBus.create16(x, y, addXYOut);
    gates.add(addXYGate);

    // AND(x,y)
    List<Pin> andXYOut = Pin.create16("andXYOut");
    AndBus andXYGate = AndBus.create16(x, y, andXYOut);
    gates.add(andXYGate);

    MuxBus muxF = new MuxBus(Pin.SIZE_16, addXYOut, andXYOut, f, out);
    gates.add(muxF);
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
