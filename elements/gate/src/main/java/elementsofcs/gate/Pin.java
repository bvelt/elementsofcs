package elementsofcs.gate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Mutable storage location for boolean value of TRUE or FALSE
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

  /*
   * Factory methods for creating pin lists of various lengths
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

  public static List<Pin> create32(String prefix) {
    return createList(prefix, SIZE_32);
  }

  public static List<Pin> create64(String prefix) {
    return createList(prefix, SIZE_64);
  }

  /**
   * Factory method for creating list of pins
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
