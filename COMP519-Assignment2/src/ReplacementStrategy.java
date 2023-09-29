public class ReplacementStrategy {
    private Strategy strategy;
    public ReplacementStrategy(int size, String type, int nrR, int nrS, int rpbR, int rpbS){
        if(type.equalsIgnoreCase("fifo")){
            this.strategy = new FIFO(size, nrR, nrS, rpbR, rpbS);
        }else if(type.equalsIgnoreCase("lifo")){
            this.strategy = new LIFO(size, nrR, nrS, rpbR, rpbS);
        }else if(type.equalsIgnoreCase("lru")){
            this.strategy = new LRU(size, nrR, nrS, rpbR, rpbS);
        }else if(type.equalsIgnoreCase("mru")){
            this.strategy = new MRU(size, nrR, nrS, rpbR, rpbS);
        }else{
            this.strategy = new RR(size, nrR, nrS, rpbR, rpbS);
        }
    }

    public void addToBuffer(Block block){
        strategy.add(block);
    }
    public boolean searchBuffer(String tableName, int recId){
        return strategy.contains(tableName, recId);
    }

}
