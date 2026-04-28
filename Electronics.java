public class Electronics extends Product {
    private int warrantyMonths;

    public Electronics(String title, float price, int warrantyMonths) {
        super(title, price, "Electronics");
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    @Override
    public void showInfo() {
        System.out.println("Электроника: " + getTitle()
                + ", цена: " + getPrice()
                + ", id: " + getId()
                + ", гарантия: " + warrantyMonths + " месяцев");
    }

    @Override
    public String toString() {
        return "Electronics{" +
                "warrantyMonths=" + warrantyMonths +
                ", id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                ", category='" + category + '\'' +
                '}';
    }
}