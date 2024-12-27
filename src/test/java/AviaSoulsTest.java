import org.example.AviaSouls;
import org.example.Ticket;
import org.example.TicketTimeComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AviaSoulsTest {
    private AviaSouls aviaSouls;

    @BeforeEach
    public void setUp() {
        aviaSouls = new AviaSouls();
    }

    @Test
    public void testCompareTo() {
        Ticket t1 = new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10, 11);
        Ticket t2 = new Ticket("Новосибирск", "Москва", 8000, 12, 16);

        aviaSouls.add(t1);
        aviaSouls.add(t2);

        Ticket[] expected = new Ticket[]{t1, t2};
        Ticket[] actual = aviaSouls.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchSortedByPrice() {
        aviaSouls.add(new Ticket("Новосибирск", "Москва", 8000, 12, 16));
        aviaSouls.add(new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10, 11));

        Ticket[] actual = aviaSouls.search("Новосибирск", "Москва");

        Ticket[] expected = {new Ticket("Новосибирск", "Москва", 8000, 12, 16)};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testTicketTimeComparator() {
        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket t1 = new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10, 11);
        Ticket t2 = new Ticket("Новосибирск", "Москва", 8000, 12, 16);
        Ticket[] actual = new Ticket[]{t2, t1};

        Arrays.sort(actual, comparator);
        Ticket[] expected = {t1, t2};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchAndSortByDuration() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("Новосибирск", "Москва", 8000, 12, 16)); // Duration: 240 minutes
        manager.add(new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10, 11)); // Duration: 65 minutes

        TicketTimeComparator comparator = new TicketTimeComparator();
        Ticket[] results = manager.searchAndSortBy("Новосибирск", "Москва", comparator);
        assertArrayEquals(new Ticket[]{
                new Ticket("Новосибирск", "Москва", 8000, 12, 16)
        }, results);

        results = manager.searchAndSortBy("Новосибирск", "Горно-Алтайск", comparator);
        assertArrayEquals(new Ticket[]{
                new Ticket("Новосибирск", "Горно-Алтайск", 4000, 10, 11)
        }, results);
    }
}