import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class TaxiDataGeneratorTest {
    @Test
    public void generatesRequestedNumberOfTaxis() {
        ArrayList<Taxi> taxis = TaxiDataGenerator.generateTaxiData(5);
        assertEquals(5, taxis.size());
        for (int i = 0; i < taxis.size(); i++) {
            assertEquals("Taxi " + (i + 1), taxis.get(i).getTaxiNumber());
            assertNotNull(taxis.get(i).getStartTime());
            assertNotNull(taxis.get(i).getEndTime());
        }
    }

    @Test
    public void generatesEmptyListForZeroTaxis() {
        assertTrue(TaxiDataGenerator.generateTaxiData(0).isEmpty());
    }
}
