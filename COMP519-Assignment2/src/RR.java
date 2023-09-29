import java.util.*;

public class RR extends Strategy{
    private List<String> buffer;
    private HashMap<String, Block> blockRecord;


    public RR(int size, int nrR, int nrS, int rpbR, int rpbS){
        this.size = size;
        this.buffer = new ArrayList<>();
        this.blockRecord = new HashMap<>(size);
        this.recordsR = nrR;
        this.recordsS = nrS;
        this.recPerBlockR = rpbR;
        this.recPerBlockS = rpbS;
    }

    @Override
    public void add(Block block) {
        //If buffer is full, remove a block randomly first
        if(isFull()){
           int randomIndex = getRandomIndex();
           String removedHash = this.buffer.get(randomIndex);

           //Remove block from bufferRecord and replace in buffer
           this.blockRecord.remove(removedHash);
           this.buffer.set(randomIndex, block.getHash());
           this.blockRecord.put(block.getHash(), block);

        }
        else{
            if(this.blockRecord.containsKey(block.getHash())) {
                //Do nothing here
                return;
            }

            //Add block to both buffer and blockRecord
            this.buffer.add(block.getHash());
            this.blockRecord.put(block.getHash(), block);
        }
    }

    public int getRandomIndex(){
        if(isEmpty())
            return -1;

        Random random = new Random();
        int index = random.nextInt(this.buffer.size()); //random index
        return  index;
    }

    @Override
    public void remove() {
    }

    @Override
    public boolean contains(String tableName, int recId) {
        int blockId = tableName.equalsIgnoreCase("r") ?
                (int) Math.ceil(recId/recPerBlockR) : (int) Math.ceil(recId/recPerBlockS);
        String searchKey = blockId + "_" + tableName;
        return blockRecord.containsKey(searchKey);
    }

    @Override
    public boolean isFull() {
        return this.buffer.size()==this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.buffer.size()==0;
    }

    @Override
    public int capacity() {
        return this.size;
    }
}
