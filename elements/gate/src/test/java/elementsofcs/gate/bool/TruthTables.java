package elementsofcs.gate.bool;

public class TruthTables {

  private TruthTables() {
  }

  // AND(A, NOT(B))
  public static final boolean[][] AANDNOTB = new boolean[][] {
      { true, true, false },
      { true, false, true },
      { false, true, false },
      { false, false, false } };

  // AND(A, B)
  public static final boolean[][] AND = new boolean[][] {
      { true, true, true },
      { true, false, false },
      { false, true, false },
      { false, false, false } };

  // CONDITIONAL(A, B) (i.e. IF A, THEN B)
  public static final boolean[][] COND = new boolean[][] {
      { true, true, true },
      { true, false, false },
      { false, true, true },
      { false, false, true } };

  public static final boolean[][] DMUX = new boolean[][] {
      // | In | Sel | A | B |
      { true, true, true, false },
      { true, false, false, true },
      { false, false, false, false },
      { false, true, false, false } };

  public static final boolean[][] FALSIFY = new boolean[][] {
      { true, false },
      { false, false } };

  public static final boolean[][] IDENTITY = new boolean[][] {
      { true, true },
      { false, false } };

  public static final boolean[][] MUX = new boolean[][] {
      // | A | B | Sel | Out |
      { true, true, true, true },
      { true, true, false, true },
      { true, false, true, true },
      { true, false, false, false },
      { false, true, true, false },
      { false, true, false, true },
      { false, false, true, false },
      { false, false, false, false } };

  public static final boolean[][] NOR = new boolean[][] {
      { true, true, false },
      { true, false, false },
      { false, true, false },
      { false, false, true } };

  public static final boolean[][] NOT = new boolean[][] {
      { true, false },
      { false, true } };

  public static final boolean[][] OR = new boolean[][] {
      { true, true, true },
      { true, false, true },
      { false, true, true },
      { false, false, false } };

  public static final boolean[][] XOR = new boolean[][] {
      { true, true, false },
      { true, false, true },
      { false, true, true },
      { false, false, false } };
}
