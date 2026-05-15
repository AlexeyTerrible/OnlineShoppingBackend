public class Clothing extends Product {
    private int size;

    public Clothing(String title, float price, int size) {
        super(title, price, "Clothing");
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void showInfo() {
        System.out.println("Одежда: " + getTitle()
                + ", цена: " + getPrice()
                + ", id: " + getId()
                + ", размер: " + size);
    }

    @Override
    public String toString() {
        return "Clothing{" +
                "size=" + size +
                ", id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}