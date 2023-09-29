import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String choice;
        Scanner scn = new Scanner(System.in);

        do{
            System.out.println("Enter number of records in r");
            int numRecords_r = scn.nextInt();

            System.out.println("Enter number of records in s");
            int numRecords_s = scn.nextInt();

            System.out.println("Enter number of records per block in r");
            int recPerBlock_r = scn.nextInt();

            System.out.println("Enter number of records per block in s");
            int recPerBlock_s = scn.nextInt();

            System.out.println("Enter number of blocks in buffer");
            int numBlocksInBuffer = scn.nextInt();

            System.out.println("Please select a buffer replacement strategy : FIFO/LIFO/LRU/MRU/RR");
            String type = scn.next();

            //create objects and call methods for join simulation
            BufferReplacementSimulation simulation = new BufferReplacementSimulation(numBlocksInBuffer, type, numRecords_r, numRecords_s, recPerBlock_r, recPerBlock_s);
            simulation.simulateJoin();

            //Print results
            System.out.println(String.format("Disk Reads: %d", simulation.getDiskReads() ));

            System.out.println("Would you like to continue? (y/n)");
            choice = scn.next();

        }while((choice.equalsIgnoreCase("y")));

        System.out.println("Program ended.");
    }
}