public class Food extends Product {
    private int calories;

    public Food(String title, float price, int calories) {
        super(title, price, "Food");
        this.calories = calories;
    }

    @Override
    public void showInfo() {
        System.out.println("Еда: " + getTitle()
                + ", цена: " + getPrice()
                + ", id: " + getId()
                + ", калории: " + calories);
    }
}