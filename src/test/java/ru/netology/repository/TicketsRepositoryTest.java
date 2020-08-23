package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class TicketsRepositoryTest {
    private TicketsRepository repository = new TicketsRepository();
    private Ticket first = new Ticket(1, 15000, "OVB", "SVO", 240);
    private Ticket second = new Ticket(2, 13000, "OVB", "SVO", 240);
    private Ticket third = new Ticket(3, 17000, "OVB", "VKO", 240);

    @BeforeEach
    public void setUp() {
        repository = new TicketsRepository();
        repository.save(first);
        repository.save(second);
        repository.save(third);
    }

    @Test
    public void shouldFindAllTickets() {
        Ticket[] expected = new Ticket[]{first, second, third};
        Ticket[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByIdIfExist() {
        int idToFind = 3;

        Ticket expected = third;
        Ticket actual = repository.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindByIdIfNotExist() {
        int idToFind = 4;

        Ticket expected = null;
        Ticket actual = repository.findById(idToFind);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdIfExist() {
        int idToRemove = 2;

        repository.removeById(idToRemove);

        Ticket[] expected = {first, third};
        Ticket[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIdIfNotExist() {
        assertThrows(NotFoundException.class, () -> repository.removeById(4));
    }
}