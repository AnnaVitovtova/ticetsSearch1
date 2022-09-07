package ru.netology.domain;

public class Repository {
    private Ticket[] tickets = new Ticket[0];

    public void save(Ticket ticket) throws AlreadyExistsException {
        if (findById(ticket.getId()) != null) {
            throw new AlreadyExistsException("Элеммента с id = " + ticket.getId() + " уже есть");
        } else {

            int length = tickets.length + 1;
            Ticket[] tmp = new Ticket[length];
            System.arraycopy(tickets, 0, tmp, 0, tickets.length);
            tmp[tmp.length - 1] = ticket;
            this.tickets = tmp;
        }
    }


    public Ticket[] findAll() {
        return tickets;
    }

    public void dalitId(int id) {
        if (findById(id) == null) {
            throw new NotFoundException("Элеммента с id = " + id + " нет");
        } else {

            int length = tickets.length - 1;
            Ticket[] tmp = new Ticket[length];
            int index = 0;
            for (Ticket item : tickets) {
                if (item.getId() != id) {
                    tmp[index] = item;

                    index++;
                }
            }
            tickets = tmp;
        }
    }

    public Ticket findById(int id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }
}

