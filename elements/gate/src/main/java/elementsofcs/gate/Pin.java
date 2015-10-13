package elementsofcs.gate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mutable storage location for boolean value. Models a binary signal that is
 * the input or output of a digital circuit.
 * 
 * @author brentvelthoen
 *
 */
public final class Pin {

  public static final int SIZE_2 = 2;
  public static final int SIZE_4 = 4;
  public static final int SIZE_8 = 8;
  public static final int SIZE_16 = 16;
  public static final int SIZE_32 = 32;
  public static final int SIZE_64 = 64;
  public static final int SIZE_128 = 128;
  public static final int SIZE_256 = 256;

  /*
   * Factory methods to create lists of pins of various lengths
   */
  public static List<Pin> create2() {
    return createList(SIZE_2);
  }

  public static List<Pin> create4() {
    return createList(SIZE_4);
  }

  public static List<Pin> create8() {
    return createList(SIZE_8);
  }

  public static List<Pin> create16() {
    return createList(SIZE_16);
  }

  public static List<Pin> create32() {
    return createList(SIZE_32);
  }

  public static List<Pin> create64() {
    return createList(SIZE_64);
  }

  /**
   * Factory method for creating list of pins initialized to false
   * 
   * @param prefix
   *          name prefix
   * @param size
   *          number of pins in list
   * @return list of pins
   */
  public static List<Pin> createList(int size) {
    if (size == 0) {
      throw new IllegalArgumentException("At least one pin required");
    }
    List<Pin> pins = new ArrayList<Pin>(size);
    for (int i = 0; i < size; i++) {
      Pin pin = new Pin();
      pins.add(pin);
    }
    return pins;
  }

  /**
   * Factory method for creating a list to contain specified pins
   * 
   * @param pins
   *          pins to include in list
   * @return list of pins
   */
  public static List<Pin> createList(Pin... pins) {
    if (pins.length == 0) {
      throw new IllegalArgumentException("At least one pin required");
    }
    List<Pin> out = new ArrayList<Pin>(pins.length);
    for (Pin p : pins) {
      out.add(p);
    }
    return out;
  }

  public static List<Pin> fill16(Pin pin) {
    return fillList(pin, SIZE_16);
  }

  /**
   * Factory method to create a list of pins of specified length where each
   * member is specified pin
   * 
   * @param pin
   *          pin to add to each position in list
   * @param size
   *          number of pins in list
   * @return list of pins
   */
  public static List<Pin> fillList(Pin pin, int size) {
    Objects.requireNonNull(pin, "pin");
    if (size == 0) {
      throw new IllegalArgumentException("At least one pin required");
    }
    List<Pin> pins = new ArrayList<Pin>(size);
    while (size-- > 0) {
      pins.add(pin);
    }
    return pins;
  }

  /**
   * Verify list of pins is of specified length or throw
   * {@link IllegalArgumentException}
   * 
   * @param pins
   *          list of pins to verify
   * @param size
   *          expected length of list of pins
   * @param name
   *          name of pin to include in exception message
   */
  public static void checkListSize(List<Pin> pins, int size, String name) {
    if (pins.size() != size) {
      throw new IllegalArgumentException("List '" + name + "' must be of size " + size);
    }
  }

  public Pin() {
    super();
  }

  public Pin(boolean value) {
    this();
    this.value = value;
  }

  private boolean value;

  public boolean getValue() {
    return value;
  }

  public void setValue(boolean value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Pin [" + value + "]";
  }
}
