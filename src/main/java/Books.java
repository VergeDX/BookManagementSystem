import com.j256.ormlite.field.DatabaseField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Books {
    @DatabaseField(id = true)
    private String ISBN;

    @DatabaseField
    private String name, press, author;

    @DatabaseField
    private int inventory;

    @DatabaseField
    private double price;

    @Override
    public String toString() {
        return ISBN + " | " + name + " | " + press + " | " + author + " | " + inventory + " | " + price;
    }
}
