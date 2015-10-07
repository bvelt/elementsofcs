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
  public static List<Pin> create2(String prefix) {
    return createList(prefix, SIZE_2);
  }

  public static List<Pin> create4(String prefix) {
    return createList(prefix, SIZE_4);
  }

  public static List<Pin> create8(String prefix) {
    return createList(prefix, SIZE_8);
  }

  public static List<Pin> create16(String prefix) {
    return createList(prefix, SIZE_16);
  }

  public static List<Pin> fill16(Pin pin) {
    return fillList(pin, SIZE_16);
  }

  public static List<Pin> create32(String prefix) {
    return createList(prefix, SIZE_32);
  }

  public static List<Pin> create64(String prefix) {
    return createList(prefix, SIZE_64);
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
  public static List<Pin> createList(String prefix, int size) {
    List<Pin> pins = new ArrayList<Pin>(size);
    for (int i = 0; i < size; i++) {
      Pin pin = new Pin(prefix + "[" + i + "]");
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
    List<Pin> out = new ArrayList<Pin>(pins.length);
    for (Pin p : pins) {
      out.add(p);
    }
    return out;
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

  private final String name;
  private boolean value;

  public Pin(String name) {
    this(name, false);
  }

  public Pin(String name, boolean value) {
    super();
    Objects.requireNonNull(name, "name");
    this.name = name;
    this.value = value;
  }

  public boolean getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  public void setValue(boolean value) {
    this.value = value;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + (value ? 1231 : 1237);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Pin))
      return false;
    Pin other = (Pin) obj;
    return Objects.equals(other.name, name)
        && other.value == value;
  }

  @Override
  public String toString() {
    return "Pin [name=" + name + ", value=" + value + "]";
  }

}
