public class Main {
    public static void main(String[] args) {

        //new Electronics("Ноутбук", 2500.0f, 24);
        //new Food("Яблоко", 2.5f, 52);
        //new Electronics("Телефон", 1200.0f, 12);

        //Product.showAllProducts();
        
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
    }
}

// TODO 
// должен быть класс для категорий в нем должны быть: 
// поле name 
// список list< > который будет содержать категории 
// добавить методы чтобы добавлять новую категорию (принимается один параметр)
// еще один метод showCategory чтобы показать категорию 

// TODO 
// новый класс каталог (хранит сами категории(список базовый категорий))
// добавить счетчик главных категорий 