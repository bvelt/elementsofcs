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

    // zx: if zx=1 then x'=0 else x'=x

    // NOT(zx)
    Pin notZXOut = new Pin("notZXOut");
    NotCompositeGate notZXGate = new NotCompositeGate(zx, notZXOut);
    gates.add(notZXGate);

    // AND(NOT(zx), x)
    AndBus zxGate = AndBus.create16(x, Pin.fill16(notZXOut), x);
    gates.add(zxGate);

    // nx: if nx=1 then x'=!x else x'=x

    // XOR(nx, x)
    XOrBus nxGate = XOrBus.create16(x, Pin.fill16(nx), x);
    gates.add(nxGate);

    // zy: if zy=1 then y'=0 else y'=y

    // NOT(zy)
    Pin notZYOut = new Pin("notZYOut");
    NotCompositeGate notZYGate = new NotCompositeGate(zy, notZYOut);
    gates.add(notZYGate);

    // AND(NOT(zy), y)
    AndBus zyGate = AndBus.create16(y, Pin.fill16(notZYOut), y);
    gates.add(zyGate);

    // zy: if zy=1 then y'=!y else y'=y

    // XOR(ny, y)
    XOrBus nyGate = XOrBus.create16(y, Pin.fill16(ny), y);
    gates.add(nyGate);

    // f: if f=1 then out=x + y ('+' = addition) else out=x & y

    // ADD(x,y)
    List<Pin> addXYOut = Pin.create16("addXYOut");
    AdderBus addXYGate = AdderBus.create16(x, y, addXYOut);
    gates.add(addXYGate);

    // AND(f, ADD(x,y))
    List<Pin> fLeftAndOut = Pin.create16("fLeftAndOut");
    AndBus fLeftAndGate = AndBus.create16(Pin.fill16(f), addXYOut, fLeftAndOut);
    gates.add(fLeftAndGate);

    // NOT(f)
    Pin notFOut = new Pin("notFOut");
    NotCompositeGate notFGate = new NotCompositeGate(f, notFOut);
    gates.add(notFGate);

    // AND(x,y)
    List<Pin> andXYOut = Pin.create16("andXYOut");
    AndBus andXYGate = AndBus.create16(x, y, andXYOut);
    gates.add(andXYGate);

    // AND(NOT(f), AND(x,y))
    List<Pin> fRightAndOut = Pin.create16("fRightAndOut");
    AndBus fRightAndGate = AndBus.create16(Pin.fill16(notFOut), andXYOut, fRightAndOut);
    gates.add(fRightAndGate);

    // OR(AND(f, ADD(x,y)), AND(NOT(F), AND(x,y)))
    OrBus fOrGate = OrBus.create16(fLeftAndOut, fRightAndOut, out);
    gates.add(fOrGate);

    // no: if no=1 then out'=!out else out'=out

    // XOR(no, out)
    XOrBus noGate = XOrBus.create16(Pin.fill16(no), out, out);
    gates.add(noGate);

    // zr: true iff out=0

    // OR(out[0],out[1]..out[15])
    Pin orOutsOut = new Pin("orOutsOut");
    OrNWayBus orOutsGate = new OrNWayBus(Pin.SIZE_16, out, orOutsOut);
    gates.add(orOutsGate);

    // NOT(OR(out[0],out[1]..out[15]))
    NotCompositeGate zrGate = new NotCompositeGate(orOutsOut, zr);
    gates.add(zrGate);

    // ng: true iff out<0

    // AND(out[0],out[0])
    AndCompositeGate andOutMSB = new AndCompositeGate(out.get(0), out.get(0), ng);
    gates.add(andOutMSB);

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
