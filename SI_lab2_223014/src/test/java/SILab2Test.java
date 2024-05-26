import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

public class SILab2Test {

    private Item createItem(String name, String barcode, int price, float discount) {
        return new Item(name, barcode, price, discount);
    }

    @Test
    void testEveryBranch() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 100));
        assertEquals("allItems list can't be null!", ex.getMessage());
        assertTrue(SILab2.checkCart(Arrays.asList(), 100));
        Item item1 = createItem(null, "123", 50, 0);
        assertTrue(SILab2.checkCart(Arrays.asList(item1), 100));
        assertEquals("unknown", item1.getName());
        Item item2 = createItem("item2", null, 50, 0);
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(Arrays.asList(item2), 100));
        assertEquals("No barcode!", ex.getMessage());
        Item item3 = createItem("item3", "12a3", 50, 0);
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(Arrays.asList(item3), 100));
        assertEquals("Invalid character in item barcode!", ex.getMessage());
        Item item4 = createItem("item4", "123", 400, 0.1f);
        assertFalse(SILab2.checkCart(Arrays.asList(item4), 30));
        assertTrue(SILab2.checkCart(Arrays.asList(item4), 50));
        Item item5 = createItem("item5", "123", 50, 0);
        assertTrue(SILab2.checkCart(Arrays.asList(item5), 100));
    }

    @Test
    void testMultipleCondition() {
        Item item1 = createItem("item1", "0123", 400, 0.1f);
        assertFalse(SILab2.checkCart(Arrays.asList(item1), 40));
        Item item2 = createItem("item2", "1234", 400, 0.1f);
        assertFalse(SILab2.checkCart(Arrays.asList(item2), 40));
        Item item3 = createItem("item3", "0123", 400, 0);
        assertFalse(SILab2.checkCart(Arrays.asList(item3), 40));
        Item item4 = createItem("item4", "1234", 400, 0);
        assertFalse(SILab2.checkCart(Arrays.asList(item4), 40));
        Item item5 = createItem("item5", "0123", 300, 0.1f);
        assertFalse(SILab2.checkCart(Arrays.asList(item5), 40));
        Item item6 = createItem("item6", "1234", 300, 0.1f);
        assertFalse(SILab2.checkCart(Arrays.asList(item6), 40));
        Item item7 = createItem("item7", "0123", 300, 0);
        assertTrue(SILab2.checkCart(Arrays.asList(item7), 40));
        Item item8 = createItem("item8", "1234", 300, 0);
        assertTrue(SILab2.checkCart(Arrays.asList(item8), 40));
    }
}