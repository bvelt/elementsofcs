package elementsofcs.gate.bool.primitive;

import static elementsofcs.gate.bool.alu.BinaryNumber.increment;
import static elementsofcs.gate.bool.alu.BinaryNumber.maximumUnsignedInt;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elementsofcs.gate.Pin;

public class PrimitiveStorageTest {

  private final List<Pin> address = Pin.create4("address");
  private final List<Integer> items = new ArrayList<Integer>();

  private PrimitiveStorage<Integer> storage = null;

  @Test
  public void incrementAddressAndGetItem() {

    int maxIndex = maximumUnsignedInt(address);
    for (int i = 0; i <= maxIndex; i++) {
      items.add(i);
    }

    storage = new PrimitiveStorage<>(address, items);

    for (int i = 0; i < items.size(); i++, increment(address)) {
      Integer expecting = new Integer(i);
      Integer actual = storage.getItem();
      assertEquals(expecting, actual);
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void rejectAddressThatIsInsufficientToSpecifyPositionsOfItems() {

    int maxIndex = maximumUnsignedInt(address) + 1;
    for (int i = 0; i <= maxIndex; i++) {
      items.add(i);
    }

    storage = new PrimitiveStorage<>(address, items);
  }
}
