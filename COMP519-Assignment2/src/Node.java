public class Node {

    private Block block;
    Node prev;
    Node next;

    public Node(Block block){
        this.block = block;
        this.prev = null;
        this.next = null;
    }

    public Block getBlock() {
        return block;
    }
}
