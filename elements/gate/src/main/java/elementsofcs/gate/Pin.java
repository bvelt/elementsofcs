package elementsofcs.gate;

import java.util.Objects;

public final class Pin {
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
  public String toString() {
    return "Pin [name=" + name + ", value=" + value + "]";
  }

}
