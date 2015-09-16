package elementsofcs.gate.bool.alu;

import java.util.List;

import elementsofcs.gate.Pin;

public class BinaryNumber {

  private BinaryNumber() {
  }

  public static long powerOf2(int exponent) {
    long acc = 1L;
    for (int i = 0; i < exponent; i++) {
      acc *= 2L;
    }
    return acc;
  }

  public static long maximumUnsignedValue(List<Pin> binaryNumber) {
    return powerOf2(binaryNumber.size());
  }

  public static long maximumSignedValue(List<Pin> binaryNumber) {
    return powerOf2(binaryNumber.size() - 1) - 1;
  }

  public static long minimumSignedValue(List<Pin> binaryNumber) {
    return powerOf2(binaryNumber.size() - 1) * -1;
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
}
