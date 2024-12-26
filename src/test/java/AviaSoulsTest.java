import org.example.AviaSouls;
import org.example.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AviaSoulsTest {
    private AviaSouls aviaSouls;

    @BeforeEach
    void setUp() {
        aviaSouls = new AviaSouls();
    }

    @Test
    void testCompareTo() {
        Ticket t1 = new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10, 11);
        Ticket t2 = new Ticket("Новосибирск", "Москва", 8000, 12, 16);

        aviaSouls.add(t1);
        aviaSouls.add(t2);

        Ticket[] expected = new Ticket[]{t1, t2};
        Ticket[] actual = aviaSouls.findAll(); // Метод findAll возвращает все билеты
        assertArrayEquals(expected, actual);
    }

    @Test
    void testSearchSortedByPrice() {
        aviaSouls.add(new Ticket("Новосибирск", "Москва", 8000, 12, 16));
        aviaSouls.add(new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10, 11));

        Ticket[] actual = aviaSouls.search("Новосибирск", "Москва");

        assertEquals("Москва", actual[0].getTo()); // Убедимся, что это билет до Москвы
        assertEquals(8000, actual[0].getPrice());  // Цена должна быть 8000
    }

    @Test
    void testTicketTimeComparator() {
        Ticket t1 = new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10, 11);
        Ticket t2 = new Ticket("Новосибирск", "Москва", 8000, 12, 16);

        Ticket[] actual = new Ticket[]{t2, t1};

        TicketTimeComparator comparator = new TicketTimeComparator();

        Arrays.sort(actual, comparator);

        assertEquals(t1, actual[0]);
        assertEquals(t2, actual[1]);
    }

    @Test
    void testSearchAndSortByDuration() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Новосибирск", "Москва", 8000, 12, 16)); // Duration: 240 minutes
        manager.add(new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10, 11)); // Duration: 65 minutes

        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] results = manager.searchAndSortBy("Новосибирск", "Москва", comparator);
        assertEquals(1, results.length);
        assertEquals(4, results[0].getTimeTo() - results[0].getTimeFrom());

        results = manager.searchAndSortBy("Новосибирск", "Горно-Алтайск", comparator);
        assertEquals(1, results.length);
        assertEquals(1, results[0].getTimeTo() - results[0].getTimeFrom());
    }
}