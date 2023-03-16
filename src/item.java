import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.nio.file.Files.write;

public class item {

    public static final String FILEPATH = "./save.txt";
    public static int idCounter = 0;
    private static Map<Integer, item> items = new HashMap<>();
    private final String name;
    private final int weight;
    public int id;
    private Integer stamina;
    private Integer health;
    private Integer healing;
    private Integer duration;
    private ArrayList<String> someList;


    public item(String name, int weight, ArrayList<String> someList) {
        this.name = name;
        this.weight = weight;
        this.someList = someList;

        id = ++idCounter;
        items.put(id, this);

    }

    public item(String name, int weight, Integer stamina, Integer health, Integer healing, Integer duration) {
        this.name = name;
        this.weight = weight;
        this.stamina = stamina;
        this.health = health;
        this.healing = healing;
        this.duration = duration;

        id = ++idCounter;
        items.put(id, this);

    }

    public static Map<Integer, item> getItems() {
        return items;
    }

    public static void saveItems() throws IOException {

        String jsonString = new Gson().toJson(item.getItems());

        Path path = Paths.get(FILEPATH);

        byte[] strToBytes = jsonString.getBytes();

        write(path, strToBytes);

    }

    public static void readSave() {

        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(FILEPATH), StandardCharsets.UTF_8)) {

            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            //handle exception
        }
        String fileContent = contentBuilder.toString();

        Type listOfMyClassObject = new TypeToken<Map<Integer, item>>() {
        }.getType();

        Gson gson = new Gson();

        items = gson.fromJson(fileContent, listOfMyClassObject);

    }

    public int healthRestored() {
        return this.healing * this.duration;
    }

    public void show() {
        System.out.println(items);
    }

    @Override
    public String toString() {
        return "item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", stamina=" + stamina +
                ", health=" + health +
                ", healing=" + healing +
                ", duration=" + duration +
                '}';
    }

}
