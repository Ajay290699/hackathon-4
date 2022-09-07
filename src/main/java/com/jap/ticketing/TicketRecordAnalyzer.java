package com.jap.ticketing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TicketRecordAnalyzer {

    public static void main(String[] args) {
        TicketRecordAnalyzer ticketRecordAnalyzer = new TicketRecordAnalyzer();
        String fileName = "sample.csv";
        List<TicketRecord> ticketRecordList = ticketRecordAnalyzer.readFile(fileName);
        System.out.println("Sorted tickets : ");
        for (TicketRecord element : ticketRecordList) {
            System.out.println(element);
        }
        System.out.println();
        System.out.println("After sorting");
        List<TicketRecord> ticketRecordList1 = ticketRecordAnalyzer.getAllTravelled_KMinDescending(ticketRecordList);
        for (TicketRecord element : ticketRecordList1) {
            System.out.println(element);
        }
        System.out.println();
        System.out.println("Total amount of ticket : " + ticketRecordAnalyzer.totalTicketAmount(ticketRecordList));
    }

    /*public static void main(String[] args) {
        String fileName = "C:\\Users\\Admin\\Desktop\\NIIT\\Course5\\hackathon-4\\sample.csv";
       // System.out.println(TicketRecordAnalyzer.readDataFromAFile(fileName));
        List ticketData = new ArrayList<>();
        ticketData = Collections.singletonList(readDataFromAFile(fileName));
        System.out.println("ticketData = " + ticketData);
    }
*/
    public List<TicketRecord> readFile(String filename) {
        List<TicketRecord> ticketRecordList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(",");
                String schedule_no = strings[0];
                String route_no = strings[1];
                int ticket_from_stop_id = Integer.parseInt(strings[2]);
                int ticket_from_stop_seq_no = Integer.parseInt(strings[3]);
                int ticket_till_stop_id = Integer.parseInt(strings[4]);
                int ticket_till_stop_seq_no = Integer.parseInt(strings[5]);
                String ticket_date = strings[6];
                String ticket_time = strings[7];
                int total_ticket_amount = Integer.parseInt(strings[8]);
                double travelled_KM = Double.parseDouble(strings[9]);

                ticketRecordList.add(new TicketRecord(schedule_no, route_no, ticket_from_stop_id, ticket_from_stop_seq_no, ticket_till_stop_id,
                        ticket_till_stop_seq_no, ticket_date, ticket_time, total_ticket_amount, travelled_KM));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticketRecordList;
    }

    public List<TicketRecord> getAllTravelled_KMinDescending(List<TicketRecord> ticketData) {
        ticketData.sort(((o1, o2) -> {
            if (o1.getTravelled_KM() == o2.getTravelled_KM()) {
                return 0;
            } else if (o1.getTravelled_KM() > o2.getTravelled_KM()) {
                return -1;
            } else {
                return 1;
            }
        }));
        return ticketData;
    }

    public double totalTicketAmount(List<TicketRecord> ticketsRecords) {

        Calculate calculate = ticketsRecords1 -> {
            double sum = 0;
            Iterator<TicketRecord> iterator = ticketsRecords1.iterator();
            while (iterator.hasNext()) {
                TicketRecord element = iterator.next();
                sum += element.getTotal_ticket_amount();
            }
            return sum;
        };
        return calculate.totalTicketAmount(ticketsRecords);
    }

}
