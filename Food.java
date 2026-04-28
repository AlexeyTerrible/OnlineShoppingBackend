public class Food extends Product {
    private int calories;

    public Food(String title, float price, int calories) {
        super(title, price, "Food");
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    @Override
    public void showInfo() {
        System.out.println("Еда: " + getTitle()
                + ", цена: " + getPrice()
                + ", id: " + getId()
                + ", калории: " + calories);
    }

    @Override
    public String toString() {
        return "Food{" +
                "calories=" + calories +
                ", id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                ", category='" + category + '\'' +
                '}';
    }
}