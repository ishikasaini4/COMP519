import java.util.Objects;
import java.util.UUID;

public class Block {

    private int id;
    private String tableName;


    public String getHash() {
        return id + "_"+ tableName;
    }

    public Block(int id, String table){
        this.id = id;
        this.tableName = table;
    }

    public int getId() {
        return id;
    }

    public String getTableName() {
        return tableName;
    }
}
