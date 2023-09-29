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

/**
 * Predictions for best buffer replacement strategy approach
 * a. (1000, 2000, 20, 5, 1)
 * Since the buffer size = 1, all the buffer replacement strategies will have same number of disks reads as at a particular time, a block from either table r or s can be read from disk to memory.
 *
 * b. (1000, 2000, 20, 5, 2)
 * Here, performance of LIFO and MRU would be slightly better than FIFO and LRU as they will require less reads for the blocks of r table as compared to reads with FIFO and LRU respectively.
 * In MRU, when buffer is full, after reading a record from r block, it is replaced since it becomes the most recently used block. This makes both the spaces in the buffer available for blocks of s table for a particular iteration of join simulation. Whereas, in LIFO, r block is not replaced for a particular iteration of join summation.
 *
 * c. (1000, 2000, 20, 5, 100)
 * Even though the buffer size has increased, FIFO removes the first block from buffer, LRU removes least recently used block which might increase the number of disks reads for repeatedly loading blocks of table r into the buffer. On the other hand, LIFO removes the last added block and MRU removes the most recently used block which in most cases would be a block of table s hence, saving the disk reads to load the same block of r to buffer. LIFO or MRU might give the best performance. Since the buffer size is large, LIFO will be able to store blocks of table s which would be needed when a new record of table r is read. Hence, LIFO might perform the best here.
 *
 * d. (1000, 2000, 20, 5, 500)
 * Here, total block for table r=50 and s=400 respectively. The buffer has 500 blocks space available. So, all the blocks can be put in buffer together. Hence, all the strategies will perform same and give disk reads = 450.
 *
 * e. (1, 20000, 20, 5, 100)
 * Table r has only 1 record, so 1 disk read will be required to read that record. Table s has 4000 blocks, only 4000 disk reads will be required corresponding to the 1 record in table r. Here, all strategies will perform the same with disk reads = 4001.
 *
 * f. (20, 20000, 20, 5, 100)
 * Total blocks for table r=1 and s=4000 respectively. For completing an entire iteration of s blocks with a record of table r, multiple disk reads would be required to re-read the r block into memory using both FIFO and LRU. With LIFO and MRU, performance would be slightly better as the last added block and most recently used block would be deleted respectively after each iteration of join simulation after the buffer gets full. LIFO or MRU will give the best performance.
 *
 * g. (10000, 5, 20, 5, 100)
 * Since buffer size is large and total blocks for table s=1, FIFO and LRU will have good performance. LIFO will also perform good due to large buffer size as the program progress, it will remove the r blocks that have been completely traversed and a no longer needed in memory. MRU on the other hand might have poor performance as after every iteration of join simulation it will repeatedly remove (when buffer gets full) the mostly recently used block which would be the block of table s. Hence, multiple reads will be required over the entire simulation to load block of table s into buffer. On the other hand, the average disk reads of RR strategy might outperform other strategies here.
 */