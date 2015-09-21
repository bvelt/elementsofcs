package elementsofcs.gate.bool.primitive;

import static elementsofcs.gate.bool.alu.BinaryNumber.maximumUnsignedInt;
import static elementsofcs.gate.bool.alu.BinaryNumber.unsignedToInt;

import java.util.List;
import java.util.Objects;

import elementsofcs.gate.Pin;

/**
 * Binary-addressable list of generic items
 * 
 * @author brentvelthoen
 *
 * @param <V>
 *          the type of item stored
 */
public class PrimitiveStorage<V> {

  // binary representation of current index
  protected final List<Pin> address;
  protected final List<V> items;

  public PrimitiveStorage(List<Pin> address, List<V> items) {
    super();
    Objects.requireNonNull(address, "address");
    this.address = address;

    Objects.requireNonNull(items, "items");
    this.items = items;

    checkAddressSize();
  }

  private void checkAddressSize() {
    int size = items.size();
    int maxIndex = size - 1;
    int maxAddressableIndex = maximumUnsignedInt(address);
    if (maxAddressableIndex < maxIndex) {
      throw new IllegalArgumentException("Size of address insufficient to specify " + size + " positions required by size of items");
    }
  }

  public List<Pin> getAddress() {
    return address;
  }

  public int getIndex() {
    return unsignedToInt(address);
  }

  public V getItem() {
    return items.get(getIndex());
  }

  public List<V> getItems() {
    return items;
  }

  @Override
  public String toString() {
    return "PrimitiveStorage [address=" + address + ", items=" + items + "]";
  }

}
