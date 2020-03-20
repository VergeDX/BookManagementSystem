package org.hydev;

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
        return "| " + ISBN + " | " + name + " | " + press + " | " + author + " | " + inventory + " | " + price + " |";
    }

    public void modify(Database.QueryKey queryKey, String value, Integer inventory, Double price) {
        switch (queryKey) {
            case ISBN:
                ISBN = value;
                break;
            case name:
                name = value;
                break;
            case press:
                press = value;
                break;
            case author:
                author = value;
                break;

            case inventory:
                this.inventory = inventory;
                break;
            case price:
                this.price = price;
                break;
        }

        Database.updateData(this);
    }
}
