package list;

public class Item {
    String name;
    int price;

    public Item() {
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "(" + name + "," + price + ")";
    }
}
