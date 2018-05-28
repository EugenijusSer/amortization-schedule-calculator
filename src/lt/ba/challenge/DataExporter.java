package lt.ba.challenge;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DataExporter {

    public void exportDataToCsv(List<Payment> schedule){
        try{
            FileWriter writer = new FileWriter("schedule.csv");

            String scheduleAsCsv = schedule.stream()
                    .map(Payment::toCsvRow)
                    .collect(Collectors.joining(System.lineSeparator()));

            writer.append("Payment #,Payment date,Remaining amount,Principal payment," +
                    "Interest payment,Total payment,Interest rate");
            writer.append(System.lineSeparator());

            writer.append(scheduleAsCsv);

            writer.flush();
            writer.close();
        }
        catch (IOException exc){
            System.out.println(exc.toString());
        }
        System.out.println(System.lineSeparator() + "Data was successfully exported to csv file");
    }
}
