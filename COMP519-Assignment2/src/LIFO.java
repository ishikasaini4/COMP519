import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class LIFO extends Strategy{
    private LinkedList<String> buffer;
    private HashMap<String, Block> blockRecord;

    public LIFO(int size, int nrR, int nrS, int rpbR, int rpbS){
        this.size = size;
        this.buffer = new LinkedList<>();
        this.blockRecord = new HashMap<>(size);
        this.recordsR = nrR;
        this.recordsS = nrS;
        this.recPerBlockR = rpbR;
        this.recPerBlockS = rpbS;
    }

    @Override
    public void add(Block block) {
        if(isFull()){
            remove();
        }
        String hash = block.getHash();
        buffer.addLast(hash);
        blockRecord.put(hash, block);

    }

    @Override
    public void remove() {
        if(isEmpty())
            return;
        String removedHashcode= buffer.removeLast();
        blockRecord.remove(removedHashcode);
    }

    @Override
    public boolean contains(String tableName, int recId) {
        int blockId = tableName.equalsIgnoreCase("r") ?
                (int) Math.ceil(recId/recPerBlockR) : (int) Math.ceil(recId/recPerBlockS);

        String searchKey = blockId + "_"+tableName;  //Objects.hash(blockId, tableName);
        return blockRecord.containsKey(searchKey);
    }

    @Override
    public boolean isFull() {
        return buffer.size()==this.size;
    }

    @Override
    public boolean isEmpty() {
        return buffer.isEmpty();
    }

    @Override
    public int capacity() {
        return this.size;
    }
}
