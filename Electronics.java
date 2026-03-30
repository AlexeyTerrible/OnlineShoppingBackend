public class Electronics extends Product {
    private int warranty;

    public Electronics(String title, float price, int warranty) {
        super(title, price, "Electronics");
        this.warranty = warranty;
    }

    @Override
    public void showInfo() {
        System.out.println("Электроника: " + getTitle()
                + ", цена: " + getPrice()
                + ", id: " + getId()
                + ", гарантия: " + warranty);
    }
}