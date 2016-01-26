package com.cdavis.web;

import com.cdavis.exceptions.NoSuchEntityException;
import com.cdavis.models.Ticket;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@RestController
public class TicketService {

    @RequestMapping( "/tickets" )
    public List<Ticket> getTicketsByCustomer() throws NoSuchEntityException {
        return getTicketsByCustomer(null);
    }

    @RequestMapping( "/exception" )
    public void throwException() throws NoSuchEntityException {
        throw new NullPointerException( "something is null" );
    }

    @RequestMapping( "/tickets/{customerId}")
    public List<Ticket> getTicketsByCustomer(@PathVariable String customerId ) throws NoSuchEntityException {
        if( customerId != null ) {
            return newArrayList( new Ticket("Star Trek", "JJ Abrams"),
                    new Ticket("Super 8", "JJ Abrams"));
        }

        throw new NoSuchEntityException( customerId );
    }
}
