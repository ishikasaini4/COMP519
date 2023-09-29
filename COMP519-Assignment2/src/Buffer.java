public class Buffer {
    private int size; //no. of blocks
    private int nrR;
    private int nrS;
    private int rpbR;
    private int rpbS;

    private String type;

    private ReplacementStrategy strategy;

    public Buffer(int size, String type, int nrR, int nrS, int rpbR, int rpbS){
        this.size = size;
        this.nrR = nrR;
        this.nrS = nrS;
        this.rpbR = rpbR;
        this.rpbS = rpbS;
        this.type = type;
        this.strategy = new ReplacementStrategy(size, type, nrR, nrS, rpbR, rpbS);
    }

    public int getSize() {
        return this.size;
    }

    public int getNrR() {
        return nrR;
    }

    public int getNrS() {
        return nrS;
    }

    public int getRpbR() {
        return rpbR;
    }

    public int getRpbS() {
        return rpbS;
    }

    public int getBlockNoR(int recId){
        return (int) Math.ceil(recId/getRpbR());
    }

    public int getBlockNoS(int recId){
       return (int) Math.ceil(recId/getRpbS());
    }

    public String getType() {
        return type;
    }

    public ReplacementStrategy getStrategy() {
        return strategy;
    }
}
