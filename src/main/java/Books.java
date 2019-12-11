import com.j256.ormlite.field.DatabaseField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Books {
    public static final Books EXAMPLE_BOOKs = new Books("ISBN", "name", "press", "author", 0, 0.00);

    @DatabaseField(id = true)
    private String ISBN;

    @DatabaseField
    private String name, press, author;

    @DatabaseField
    private int inventory;
    @DatabaseField
    private double price;
}
