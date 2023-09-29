import java.util.Scanner;

public class BufferReplacementSimulation {
    private BufferManager bufferManager;

    public BufferReplacementSimulation(int size, String type, int nrR, int nrS, int rpbR, int rpbS) {
        this.bufferManager = new BufferManager(size, type, nrR, nrS, rpbR, rpbS);
    }

    public int getDiskReads() {
        return bufferManager.getDiskReads();
    }

    public void simulateJoin() {

        int repeat = 1;

        //Repeat simulation 25 times when strategy type is Random Replacement(RR)
        if(bufferManager.getBuffer().getType().equalsIgnoreCase("rr"))
            repeat = 25;

        int totalDiskReads = 0;

        for(int r = 1; r<=repeat; r++) {

            //Look for  R's block in buffer
            for (int i = 0; i < bufferManager.getBuffer().getNrR(); i++) {

                boolean isRecInR = bufferManager.getBuffer().getStrategy().searchBuffer("r", i);

                //If block is not in buffer, add block in buffer and increase disk reads
                if (!isRecInR) {
                    bufferManager.setDiskReads(bufferManager.getDiskReads() + 1);
                    bufferManager.getBuffer().getStrategy().addToBuffer(new Block(bufferManager.getBuffer().getBlockNoR(i), "r"));
                }

                //Look for S's blocks in buffer
                for (int j = 0; j < bufferManager.getBuffer().getNrS(); j++) {

                    boolean isRecInS = bufferManager.getBuffer().getStrategy().searchBuffer("s", j);

                    //If block is not in buffer, add block in buffer and increase disk reads
                    if (!isRecInS) {
                        bufferManager.setDiskReads(bufferManager.getDiskReads() + 1);
                        bufferManager.getBuffer().getStrategy().addToBuffer(new Block(bufferManager.getBuffer().getBlockNoS(j), "s"));
                    }
                }
            }
            totalDiskReads+= bufferManager.getDiskReads();

            //Set disk reads to 0 for next iteration
            bufferManager.setDiskReads(0);
        }
        bufferManager.setDiskReads(totalDiskReads/repeat);
    }
}
