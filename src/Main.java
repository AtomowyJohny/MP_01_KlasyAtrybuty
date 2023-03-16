import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        //item.readSave();
        //System.out.println(item.getItems().toString());

       new item("knife", 1, new ArrayList<>());
       new item("potato", 2, null, 20, 5, 3);
       new item("tomato", 4, 20, 10, 2, 4);


        item.saveItems();

    }
}
