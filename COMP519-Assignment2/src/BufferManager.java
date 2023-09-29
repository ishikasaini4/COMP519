public class BufferManager {
    private int diskReads;
    private Buffer buffer;

    public BufferManager(int size, String type, int nrR, int nrS, int rpbR, int rpbS){
        this.diskReads = 0;
        this.buffer = new Buffer(size, type, nrR, nrS, rpbR, rpbS);
    }

    public int getDiskReads() {
        return this.diskReads;
    }

    public void setDiskReads(int diskReads) {
        this.diskReads = diskReads;
    }

    public Buffer getBuffer() {
        return this.buffer;
    }
}