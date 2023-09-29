public abstract class Strategy {
    public int size; // buffer size

    public int recordsR; // no.of records in table r
    public int recordsS; //no. of records in table s

    public int recPerBlockR; //no. of records per block in table r

    public int recPerBlockS; //no. of records per block in table s

    public abstract void add(Block block); //adds block to buffer
    public abstract void remove(); //removes block from buffer
    public abstract boolean contains(String tableName, int recId); //searches a record in buffer
    public abstract boolean isFull(); //returns true if buffer is full
    public abstract boolean isEmpty(); //returns true if buffer is empty
    public abstract int capacity(); //returns the capacity of the buffer

}
