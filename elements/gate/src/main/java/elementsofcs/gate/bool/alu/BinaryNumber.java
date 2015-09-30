package elementsofcs.gate.bool.alu;

import java.util.List;

import elementsofcs.gate.Pin;

public class BinaryNumber {

  public static List<Pin> createOne(int size) {
    List<Pin> one = Pin.createList("one", size);
    one.get(one.size() - 1).setValue(true);
    return one;
  }

  public static void increment(List<Pin> binaryNumber) {
    boolean carry = true;
    for (int i = binaryNumber.size() - 1; i >= 0 && carry; i--) {
      Pin bit = binaryNumber.get(i);
      boolean bitValue = bit.getValue();
      boolean sum = bitValue ^ carry;
      carry = bitValue && carry;
      bit.setValue(sum);
    }
  }

  public static void increment(List<Pin> binaryNumber, int times) {
    for (int i = 0; i < times; i++) {
      increment(binaryNumber);
    }
  }

  public static long maximumSignedLong(List<Pin> binaryNumber) {
    return powerOf2AsLong(binaryNumber.size() - 1) - 1;
  }

  public static int maximumUnsignedInt(List<Pin> binaryNumber) {
    return (int) maximumUnsignedLong(binaryNumber);
  }

  public static long maximumUnsignedLong(List<Pin> binaryNumber) {
    return powerOf2AsLong(binaryNumber.size()) - 1L;
  }

  public static long minimumSignedLong(List<Pin> binaryNumber) {
    return powerOf2AsLong(binaryNumber.size() - 1) * -1;
  }

  public static long powerOf2AsLong(int exponent) {
    long acc = 1L;
    for (int i = 0; i < exponent; i++) {
      acc *= 2L;
    }
    return acc;
  }

  public static int unsignedToInt(List<Pin> binaryNumber) {
    return (int) unsignedToLong(binaryNumber);
  }

  public static long unsignedToLong(List<Pin> binaryNumber) {
    long result = 0L;
    long acc = 1L;
    for (int i = binaryNumber.size() - 1; i >= 0; i--, acc *= 2L) {
      if (binaryNumber.get(i).getValue()) {
        result += acc;
      }
    }
    return result;
  }

  private BinaryNumber() {
  }
}
