public class Main {
    public static void main(String[] args) {
        Category electronics = new Category("Electronics");
        Category food = new Category("Food");
        Category clothing = new Category("Clothing");

        Category phones = new Category("Phones");
        Category laptops = new Category("Laptops");
        Category fruits = new Category("Fruits");

        electronics.addCategory(phones);
        electronics.addCategory(laptops);
        food.addCategory(fruits);

        Catalog catalog = new Catalog();
        catalog.addBaseCategory(electronics);
        catalog.addBaseCategory(food);
        catalog.addBaseCategory(clothing);

        catalog.showCatalog();

        System.out.println();
        electronics.showCategory();

        System.out.println();
        food.showCategory();

        System.out.println();
        clothing.showCategory();

        new Electronics("Macbook pro", 2599.0f, 24);
        new Food("Яблоко", 2.5f, 52);
        new Electronics("Iphone 17", 799.0f, 12);

        Product.showAllProducts();
    }
}

// TODO 
// в классах переопределить этот метод (@Override)
// создать несколько объектов разных типов (товары) для каждой категории и их положить в список list (add)
//сделать так чтобы работал showInfo то есть после добавления определенного товара этот метод выводил в консоль то что было добавлено