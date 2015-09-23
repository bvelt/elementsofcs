package elementsofcs.gate.bool.alu;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class ALUTest {

  private final List<Pin> x = Pin.create16("x");
  private final List<Pin> y = Pin.create16("y");

  private final Pin zx = new Pin("zx");
  private final Pin nx = new Pin("nx");
  private final Pin zy = new Pin("zy");
  private final Pin ny = new Pin("ny");
  private final Pin f = new Pin("f");
  private final Pin no = new Pin("no");

  private final List<Pin> out = Pin.create16("out");

  private final Pin zr = new Pin("zr");
  private final Pin ng = new Pin("ng");

  private final ALU alu = new ALU(x, y, zx, nx, zy, ny, f, no, out, zr, ng);

  @Test
  public void zx() {

    // | zx | x | x' |
    final boolean tt[][] = new boolean[][] {
        { true, false, false },
        { true, true, false },
        { false, false, false },
        { false, true, true }
    };

    for (int i = 0; i < tt.length; i++) {
      alu.reset();

      final boolean bzx = tt[i][0];
      final boolean bx = tt[i][1];
      final boolean bxout = tt[i][2];

      zx.setValue(bzx);
      x.forEach(p -> p.setValue(bx));

      alu.eval();

      assertTrue(x.stream().allMatch(p -> p.getValue() == bxout));

    }
  }

  @Test
  public void nx() {

    // | nx | x | x' |
    final boolean tt[][] = new boolean[][] {
        { true, false, true },
        { true, true, false },
        { false, false, false },
        { false, true, true }
    };

    for (int i = 0; i < tt.length; i++) {
      alu.reset();

      final boolean bnx = tt[i][0];
      final boolean bx = tt[i][1];
      final boolean bxout = tt[i][2];

      nx.setValue(bnx);
      x.forEach(p -> p.setValue(bx));

      alu.eval();

      assertTrue(x.stream().allMatch(p -> p.getValue() == bxout));
    }
  }

  @Test
  public void zy() {

    // | zy | y | y' |
    final boolean tt[][] = new boolean[][] {
        { true, false, false },
        { true, true, false },
        { false, false, false },
        { false, true, true }
    };

    for (int i = 0; i < tt.length; i++) {
      alu.reset();

      final boolean bzy = tt[i][0];
      final boolean by = tt[i][1];
      final boolean byout = tt[i][2];

      zy.setValue(bzy);
      y.forEach(p -> p.setValue(by));

      alu.eval();

      assertTrue(y.stream().allMatch(p -> p.getValue() == byout));
    }
  }

  @Test
  public void ny() {

    // | ny | y | y' |
    final boolean tt[][] = new boolean[][] {
        { true, false, true },
        { true, true, false },
        { false, false, false },
        { false, true, true }
    };

    for (int i = 0; i < tt.length; i++) {
      alu.reset();

      final boolean bny = tt[i][0];
      final boolean by = tt[i][1];
      final boolean byout = tt[i][2];

      ny.setValue(bny);
      y.forEach(p -> p.setValue(by));

      alu.eval();

      assertTrue(y.stream().allMatch(p -> p.getValue() == byout));
    }
  }

  @Test
  public void f() {

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
      alu.reset();

      final boolean bf = tt[i][0];
      final boolean bx = tt[i][1];
      final boolean by = tt[i][2];
      final boolean bout = tt[i][3];

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
  public void no() {

    // | no | out | out' |
    final boolean tt[][] = new boolean[][] {
        { true, false, true },
        { true, true, false },
        { false, false, false },
        { false, true, true }
    };

    for (int i = 0; i < tt.length; i++) {
      alu.reset();

      final boolean bno = tt[i][0];
      final boolean bout = tt[i][1];
      final boolean boutnext = tt[i][2];

      // negate x,y if need to set out=true
      nx.setValue(bout);
      ny.setValue(bout);

      no.setValue(bno);

      alu.eval();

      assertTrue(out.stream().allMatch(p -> p.getValue() == boutnext));
    }
  }

  @Test
  public void zr() {

    // | out | zr |
    final boolean tt[][] = new boolean[][] {
        { true, false },
        { false, true }
    };

    for (int i = 0; i < tt.length; i++) {
      alu.reset();
      final boolean bout = tt[i][0];
      final boolean bzr = tt[i][1];

      // negate x,y if need to set out=true, else out=false
      nx.setValue(bout);
      ny.setValue(bout);

      alu.eval();

      assertTrue(zr.getValue() == bzr);
    }
  }

  @Test
  public void ng() {

    // | out | ng |
    final boolean tt[][] = new boolean[][] {
        { true, true },
        { false, false }
    };

    for (int i = 0; i < tt.length; i++) {
      alu.reset();
      final boolean bout = tt[i][0];
      final boolean bzr = tt[i][1];

      // negate x,y if need to set out=true, else out=false
      nx.setValue(bout);
      ny.setValue(bout);

      alu.eval();

      assertTrue(ng.getValue() == bzr);
    }
  }
}
