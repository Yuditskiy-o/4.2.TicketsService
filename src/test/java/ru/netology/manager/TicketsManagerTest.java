package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.domain.TicketByTimeAscComparator;
import ru.netology.repository.TicketsRepository;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class TicketsManagerTest {
    private TicketsRepository repository = new TicketsRepository();
    private TicketsManager manager = new TicketsManager(repository);
    private Ticket first = new Ticket(1, 15000, "OVB", "SVO", 240);
    private Ticket second = new Ticket(2, 13000, "OVB", "SVO", 220);
    private Ticket third = new Ticket(3, 17000, "OVB", "VKO", 240);

    @BeforeEach
    public void setUp() {
        manager = new TicketsManager(repository);
    }

    @Test
    public void shouldReturnEmptyIfNoTickets() {
        manager.ticketAdd(first);
        manager.ticketAdd(second);
        manager.ticketAdd(third);
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.findAllSortPrice("DME", "LED");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnTicketIfContains() {
        manager.ticketAdd(first);
        Ticket[] expected = new Ticket[]{first};
        Ticket[] actual = manager.findAllSortPrice("OVB", "SVO");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketsByPriceIfContains() {
        manager.ticketAdd(first);
        manager.ticketAdd(second);
        manager.ticketAdd(third);
        Ticket[] expected = new Ticket[]{second, first};
        Ticket[] actual = manager.findAllSortPrice("OVB", "SVO");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortTicketsByTravelTime() {
        manager.ticketAdd(first);
        manager.ticketAdd(second);
        Comparator<Ticket> comparator = new TicketByTimeAscComparator();
        Ticket[] expected = new Ticket[]{second, first};
        Ticket[] actual = manager.findAllSortTime("OVB", "SVO", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeEmptyIfNoTickets() {
        manager.ticketAdd(first);
        manager.ticketAdd(second);
        manager.ticketAdd(third);
        Comparator<Ticket> comparator = new TicketByTimeAscComparator();
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.findAllSortTime("DME", "LED", comparator);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnOneIfContains() {
        manager.ticketAdd(first);
        manager.ticketAdd(third);
        Comparator<Ticket> comparator = new TicketByTimeAscComparator();
        Ticket[] expected = new Ticket[]{first};
        Ticket[] actual = manager.findAllSortTime("OVB", "SVO", comparator);
        assertArrayEquals(expected, actual);
    }
}