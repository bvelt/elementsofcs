package elementsofcs.gate.bool.alu;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class ALUTest {

  private final List<Pin> x = Pin.create16();
  private final List<Pin> y = Pin.create16();

  private final Pin zx = new Pin();
  private final Pin nx = new Pin();
  private final Pin zy = new Pin();
  private final Pin ny = new Pin();
  private final Pin f = new Pin();
  private final Pin no = new Pin();

  private final List<Pin> out = Pin.create16();

  private final Pin zr = new Pin();
  private final Pin ng = new Pin();

  private final ALU alu = new ALU(x, y, zx, nx, zy, ny, f, no, out, zr, ng);

  @Test
  public void zeroXInputIfZXIsTrue() {
    zeroInputIfSelectIsTrue(x, zx, nx);
  }

  @Test
  public void zeroYInputIfZYIsTrue() {
    zeroInputIfSelectIsTrue(y, zy, ny);
  }

  protected void zeroInputIfSelectIsTrue(List<Pin> in, Pin sel, Pin seln) {
    // | sel | seln | in | in' |
    final boolean tt[][] = new boolean[][] {
        { true, true, false, false },
        { true, true, true, false },
        { true, false, false, false },
        { true, false, true, false },
        { false, false, false, false },
        { false, false, true, true }
    };

    for (int i = 0; i < tt.length; i++) {
      final boolean bsel = tt[i][0];
      // negation control bit should be ignored as long as zero control bit is 1
      final boolean bseln = tt[i][1];
      final boolean bin = tt[i][2];
      final boolean binnext = tt[i][3];

      alu.reset();

      sel.setValue(bsel);
      seln.setValue(bseln);

      in.forEach(p -> p.setValue(bin));

      alu.eval();

      assertTrue(in.stream().allMatch(p -> p.getValue() == binnext));
    }
  }

  @Test
  public void negateXInputIfNXIsTrue() {
    negateInputIfSelectIsTrue(x, nx, zx);
  }

  @Test
  public void negateYInputIfNYIsTrue() {
    negateInputIfSelectIsTrue(y, ny, zy);
  }

  protected void negateInputIfSelectIsTrue(List<Pin> in, Pin sel, Pin selz) {

    // | sel | selz | in | in' |
    final boolean tt[][] = new boolean[][] {
        { true, false, false, true },
        { true, false, true, false },
        { false, false, false, false },
        { false, false, true, true }
    };

    for (int i = 0; i < tt.length; i++) {
      final boolean bsel = tt[i][0];
      // zero control bit has to be false for negation control bit to be enabled
      final boolean bselz = tt[i][1];
      final boolean bin = tt[i][2];
      final boolean binnext = tt[i][3];

      alu.reset();

      sel.setValue(bsel);
      selz.setValue(bselz);

      in.forEach(p -> p.setValue(bin));

      alu.eval();

      assertTrue(in.stream().allMatch(p -> p.getValue() == binnext));
    }
  }

  @Test
  public void addXYIfFBitIsTrueElseAndXY() {

    // | f | x | y | out |
    final boolean tt[][] = new boolean[][] {
        { true, false, true, true },
        { true, true, false, true },
        { true, false, false, false },
        { true, true, true, false },
        { false, false, true, false },
        { false, true, false, false },
        { false, false, false, false },
        { false, true, true, true }
    };

    for (int i = 0; i < tt.length; i++) {
      final boolean bf = tt[i][0];
      final boolean bx = tt[i][1];
      final boolean by = tt[i][2];
      final boolean bout = tt[i][3];

      alu.reset();

      f.setValue(bf);
      // just set least-significant bit
      x.get(x.size() - 1).setValue(bx);
      y.get(y.size() - 1).setValue(by);

      alu.eval();

      // just check least-significant bit
      assertTrue(out.get(out.size() - 1).getValue() == bout);
    }
  }

  @Test
  public void negateOutputIfNoBitIsTrue() {

    // | no | out | out' |
    final boolean tt[][] = new boolean[][] {
        { true, false, true },
        { true, true, false },
        { false, false, false },
        { false, true, true }
    };

    for (int i = 0; i < tt.length; i++) {
      final boolean bno = tt[i][0];
      final boolean bout = tt[i][1];
      final boolean boutnext = tt[i][2];

      alu.reset();

      // negate x,y if need to set out=true
      nx.setValue(bout);
      ny.setValue(bout);

      no.setValue(bno);

      alu.eval();

      assertTrue(out.stream().allMatch(p -> p.getValue() == boutnext));
    }
  }

  @Test
  public void zrBitIsTrueIfOutputIsEqualToZero() {

    // | out | zr |
    final boolean tt[][] = new boolean[][] {
        { true, false },
        { false, true }
    };

    for (int i = 0; i < tt.length; i++) {
      final boolean bout = tt[i][0];
      final boolean bzr = tt[i][1];

      alu.reset();

      // negate x,y if need to set out=true, else out=false
      nx.setValue(bout);
      ny.setValue(bout);

      alu.eval();

      assertTrue(zr.getValue() == bzr);
    }
  }

  @Test
  public void ngBitIsTrueIfOutputIsLessThanZero() {

    // | out | ng |
    final boolean tt[][] = new boolean[][] {
        { true, true },
        { false, false }
    };

    for (int i = 0; i < tt.length; i++) {
      final boolean bout = tt[i][0];
      final boolean bzr = tt[i][1];

      alu.reset();

      // negate x,y if need to set out=true, else out=false
      nx.setValue(bout);
      ny.setValue(bout);

      alu.eval();

      assertTrue(ng.getValue() == bzr);
    }
  }
}
