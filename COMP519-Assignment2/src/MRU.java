import java.util.HashMap;
import java.util.Map;

public class MRU extends Strategy {

    private Node head;
    private Node tail;

    private Map<String, Node> buffer;


    public MRU(int size, int nrR, int nrS, int rpbR, int rpbS){
        this.buffer = new HashMap<>(size);
        this.head = new Node(null);
        this.tail = new Node(null);
        this.size = size;
        this.recordsR = nrR;
        this.recordsS = nrS;
        this.recPerBlockR = rpbR;
        this.recPerBlockS = rpbS;

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }


    public void add(Block block){
        //If Buffer is full, remove most recently used block (from head of the list)
        if(isFull()) {
            Node rem = removeNode(this.head.next);
            buffer.remove(rem.getBlock().getHash());
        }

        //If block is already in buffer, move it to front of the buffer.
        if(buffer.containsKey(block.getHash())){
            removeNode(buffer.get(block.getHash()));
            Node toMoveAtHead = buffer.get(block.getHash());
            addToHead(toMoveAtHead);
        }else {
            addToHead(block);
        }
    }

    private Node removeNode(Node node){     //Delete a node from list
        if(node==null)
            return null;

        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }

    private void addToHead(Node node){      //For blocks already present in buffer
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next.prev = node;
        this.head.next = node;
    }

    private void addToHead(Block block){    //For new blocks to be added in buffer
        Node node = new Node(block);

        node.next = this.head.next;
        node.prev = this.head;
        this.head.next.prev = node;
        this.head.next = node;

        buffer.put(block.getHash(), node);
    }

    @Override
    public void remove() {
    }

    @Override
    public boolean contains(String tableName, int recId) {
        //Calculate block number of the record
        int blockId = tableName.equalsIgnoreCase("r") ?
                (int) Math.ceil(recId/recPerBlockR) : (int) Math.ceil(recId/recPerBlockS);
        String searchKey = blockId + "_" + tableName;

        boolean contains = buffer.containsKey(searchKey);

        //if block is in buffer, move it to front of list
        if(contains){
            Node rem = removeNode(buffer.get(searchKey));
            addToHead(rem);
        }
        return contains;
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
