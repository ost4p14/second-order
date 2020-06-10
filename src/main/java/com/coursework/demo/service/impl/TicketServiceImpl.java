package com.coursework.demo.service.impl;

import com.coursework.demo.entity.Ticket;
import com.coursework.demo.repository.TicketRepository;
import com.coursework.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class TicketServiceImpl implements TicketService {

    TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket getById(Long id) {
        return ticketRepository.findById(id).get();
    }

    @Override
    public List<Ticket> getAll() {
        return (List<Ticket>) ticketRepository.findAll();
    }

    @Override
    public Ticket update(Ticket object) {
        return ticketRepository.save(object);
    }

    @Override
    public Ticket save(Ticket object) {
        return ticketRepository.save(object);
    }

    @Override
    public Ticket delete(Ticket object) {
        ticketRepository.delete(object);
        return object;
    }
}
