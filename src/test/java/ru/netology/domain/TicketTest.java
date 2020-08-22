package ru.netology.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {
    private Ticket first = new Ticket(1, 15000, "OVB", "SVO", 240);
    private Ticket second = new Ticket(2, 13000, "OVB", "DME", 240);
    private Ticket third = new Ticket(3, 17000, "OVB", "VKO", 240);

    @Test
    public void shouldSortTicketsByPrice() {
        Ticket[] expected = new Ticket[]{second, first, third};
        Ticket[] actual = new Ticket[]{first, second, third};

        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }
}