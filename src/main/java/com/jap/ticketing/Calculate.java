package com.jap.ticketing;

import java.util.List;

public interface Calculate {
    double totalTicketAmount(List<TicketRecord> ticketRecords);
}
