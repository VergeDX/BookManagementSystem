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
    public static final Books EXAMPLE_BOOKs =
            new Books("9787111213826",
                    "JAVA 编程思想（第 4 版）",
                    "机械工业 出版社",
                    "[美]埃克尔",
                    154,
                    70.20);

    @DatabaseField(id = true)
    private String ISBN;

    @DatabaseField
    private String name, press, author;

    @DatabaseField
    private int inventory;
    @DatabaseField
    private double price;

    public String getAllInformation() {
        return this.ISBN + " | "
                + this.name + " | "
                + this.press + " | "
                + this.author + " | "
                + this.inventory + " | "
                + this.price;
    }
}
