import org.example.AviaSouls;
import org.example.Ticket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AviaSoulsTest {

    @Test
    public void testCompareTo() {
      Ticket t1 = new Ticket("Новосибирск", "Москва", 8000, 12_00, 16_00);
      Ticket t2 = new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10_15, 11_20);
      assertTrue(t1.compareTo(t2) > 0);
      assertTrue(t2.compareTo(t1) < 0);
    }

  @Test
  void testSearchSortedByPrice() {
    AviaSouls manager = new AviaSouls();
    manager.add(new Ticket("Новосибирск", "Москва", 8000, 12_00, 16_00));
    manager.add(new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10_15, 11_20));

    Ticket[] results = manager.search("Новосибирск", "Москва");
    assertEquals(1, results.length);
    assertEquals(8000, results[0].getPrice());

    results = manager.search("Новосибирск", "Горно-Алтайск");
    assertEquals(1, results.length);
    assertEquals(4000, results[0].getPrice());
  }

  @Test
  void testTicketTimeComparator() {
    Ticket t1 = new Ticket("Новосибирск", "Москва", 8000, 12_00, 16_00);
    Ticket t2 = new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10_15, 11_20);
    TicketTimeComparator comparator = new TicketTimeComparator();
    assertTrue(comparator.compare(t1, t2) > 0);
    assertTrue(comparator.compare(t2, t1) < 0);
  }

  @Test
  void testSearchAndSortByDuration() {
    AviaSouls manager = new AviaSouls();
    manager.add(new Ticket("Новосибирск", "Москва", 8000, 720, 960)); // Duration: 240 minutes
    manager.add(new Ticket("Новосибирск", "Горно-Алтайск", 4000, 615, 680)); // Duration: 65 minutes

    TicketTimeComparator comparator = new TicketTimeComparator();
    Ticket[] results = manager.searchAndSortBy("Новосибирск", "Москва", comparator);
    assertEquals(1, results.length);
    assertEquals(240, results[0].getTimeTo() - results[0].getTimeFrom());

    results = manager.searchAndSortBy("Новосибирск", "Горно-Алтайск", comparator);
    assertEquals(1, results.length);
    assertEquals(65, results[0].getTimeTo() - results[0].getTimeFrom());
  }
}

