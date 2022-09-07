package ru.netology.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {
    private Ticket aaaBbb = new Ticket(1, 1000, "AAA", "BBB", 150);
    private Ticket cccBbb = new Ticket(2, 1200, "CCC", "BBB", 200);
    private Ticket aAaBbb = new Ticket(3, 800, "AAA", "BBB", 100);
    private Ticket aaaCcc = new Ticket(4, 2000, "AAA", "CCC", 250);

    @Test
    void add() throws AlreadyExistsException {
        Manager manager = new Manager();
        manager.add(aaaBbb);
        manager.add(aAaBbb);
        manager.add(aaaCcc);
        manager.add(cccBbb);

        Ticket[] actual = manager.findAll();
        Ticket[] expected = {aaaBbb, aAaBbb, aaaCcc, cccBbb};
        assertArrayEquals(expected, actual);
    }

    @Test
    void addNull() throws AlreadyExistsException {
        Repository repo = new Repository();
        repo.save(aaaBbb);
        repo.save(aAaBbb);
        repo.save(aaaCcc);
        repo.save(cccBbb);

        assertThrows(AlreadyExistsException.class, () -> {
            repo.save(aAaBbb);
        });
    }

    @Test
    void dall() throws AlreadyExistsException {
        Manager manager = new Manager();
        manager.add(aaaBbb);
        manager.add(aAaBbb);
        manager.add(aaaCcc);
        manager.add(cccBbb);

        manager.dall(3);

        Ticket[] actual = manager.findAll();
        Ticket[] expected = {aaaBbb, aaaCcc, cccBbb};
        assertArrayEquals(expected, actual);
    }

    @Test
    void dallNull() throws AlreadyExistsException {
        Manager manager = new Manager();
        manager.add(aaaBbb);
        manager.add(aAaBbb);
        manager.add(aaaCcc);
        manager.add(cccBbb);

        assertThrows(NotFoundException.class, () -> {
            manager.dall(5);
        });
    }

    @Test
    void findAll1() throws AlreadyExistsException {
        Manager manager = new Manager();
        manager.add(aaaBbb);
        manager.add(aAaBbb);
        manager.add(aaaCcc);
        manager.add(cccBbb);

        Ticket[] actual = manager.findAll1("AAA", "BBB");
        Ticket[] expected = {aAaBbb, aaaBbb};
        assertArrayEquals(expected, actual);
    }

    @Test
    void findAll12() throws AlreadyExistsException {
        Manager manager = new Manager();
        manager.add(aaaBbb);
        manager.add(aAaBbb);
        manager.add(aaaCcc);
        manager.add(cccBbb);

        Ticket[] actual = manager.findAll1("AAA", "CCC");
        Ticket[] expected = {aaaCcc};
        assertArrayEquals(expected, actual);
    }

    @Test
    void findAll123() throws AlreadyExistsException {
        Manager manager = new Manager();
        manager.add(aaaBbb);
        manager.add(aAaBbb);
        manager.add(aaaCcc);
        manager.add(cccBbb);

        Ticket[] actual = manager.findAll1("CCC", "BBB");
        Ticket[] expected = {cccBbb};
        assertArrayEquals(expected, actual);
    }

    @Test
    void findAll1Null() throws AlreadyExistsException {
        Manager manager = new Manager();
        manager.add(aaaBbb);
        manager.add(aAaBbb);
        manager.add(aaaCcc);
        manager.add(cccBbb);

        assertThrows(NotFoundException.class, () -> {
            manager.findAll1("DDD", "FFF");
        });
    }

}