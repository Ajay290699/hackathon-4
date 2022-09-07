package com.jap.ticketing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TicketRecordAnalyzerTest {
    TicketRecord ticketRecord;
    TicketRecordAnalyzer ticketRecordAnalyzer;

    String filename = "sample.csv";
    String filename2 = "simple.csv";

    @Before
    public void setUp() throws Exception {
        ticketRecordAnalyzer = new TicketRecordAnalyzer();
        ticketRecord = new TicketRecord("KIAS-12/5", "KIAS-12UP", 9387, 1, 11359, 39, "01/09/2018", "02:02:58", 281, 49.3);
    }

    @After
    public void tearDown() throws Exception {
        ticketRecordAnalyzer = null;
        ticketRecord = null;
    }

    @Test
    public void readFile() {
        List<TicketRecord> actual = ticketRecordAnalyzer.readFile(filename);
        assertEquals("Ticket Records objects not returned correctly", 49, actual.size());
    }

    @Test
    public void sortDataByDistanceTravelled() {
        List<TicketRecord> actual = ticketRecordAnalyzer.readFile(filename);
        assertEquals(49.5, ticketRecordAnalyzer.getAllTravelled_KMinDescending(actual).get(0).getTravelled_KM(), 0);
    }

    @Test
    public void totalTicketAmount() {
        List<TicketRecord> actual = ticketRecordAnalyzer.readFile(filename);
        assertEquals(10348, ticketRecordAnalyzer.totalTicketAmount(actual), 0);
    }
}