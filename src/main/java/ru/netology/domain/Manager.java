package ru.netology.domain;
import java.util.Arrays;
import java.util.Comparator;

public class Manager implements Cloneable {
    private Repository repository = new Repository();

    public Manager() {
    }


    public void add(Ticket ticket) throws AlreadyExistsException {
        repository.save(ticket);
    }

    public Ticket[] findAll() {
        return repository.findAll();
    }

    public void dall(int id) {
        repository.dalitId(id);
    }


    public Ticket[] findAll1(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : repository.findAll()) {
            if (matchesFromTo(ticket, from, to)) {
                Ticket[] tmp = new Ticket[result.length + 1];
                int i = 0;
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[result.length] = ticket;
                result = tmp;
            }
        }
        if (result.length == 0) {
            throw new NotFoundException("not ticket");
        }
        Arrays.sort(result);
        return result;
    }


    public boolean matchesFromTo(Ticket ticket, String from, String to) {
        if (ticket.getFrom().contains(from) && ticket.getTo().contains(to)) {
            return true;
        }
        return false;

    }
}

