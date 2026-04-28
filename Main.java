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

        // Создаем продукты
        Electronics macbook = new Electronics("Macbook pro", 2599.0f, 24);
        Food apple = new Food("Яблоко", 2.5f, 52);
        Electronics iphone = new Electronics("Iphone 17", 799.0f, 12);
        Food bread = new Food("Хлеб", 1.2f, 150);
        Food banana = new Food("Банан", 1.8f, 95);
        Electronics headphones = new Electronics("Наушники Sony", 149.0f, 12);

        // Вывод всех продуктов
        System.out.println("\n=== Все продукты ===");
        Product.showAllProducts();

        // Тестирование Customer с новыми возможностями
        System.out.println("\n=== Тестирование Customer ===");

        Customer customer = new Customer("Иван", "Петров", 1990, 1000.0);
        System.out.println("Создан покупатель: " + customer);

        // Добавляем товары в корзину
        System.out.println("\n--- Добавление товаров в корзину ---");
        customer.addProduct(apple);
        customer.addProduct(bread);
        customer.addProduct(iphone);
        customer.addProduct(banana);

        // Показываем корзину (StreamAPI)
        System.out.println("\n--- Содержимое корзины (StreamAPI) ---");
        customer.showCart();

        // Статистика корзины (StreamAPI)
        customer.showCartStatistics();

        // Показываем доступность товаров (новое меню)
        customer.showProductAvailability();

        // Поиск товаров (StreamAPI)
        customer.searchProducts("iphone");

        // Фильтрация по цене (StreamAPI)
        customer.showFilteredProducts(100.0);

        // Статистика цен (StreamAPI)
        Product.showPriceStatistics();

        // Оформляем первый заказ
        System.out.println("\n--- Оформление первой покупки ---");
        customer.checkout();

        // Добавляем еще товары
        customer.addProduct(macbook);
        customer.addProduct(headphones);

        // Оформляем второй заказ
        System.out.println("\n--- Оформление второй покупки ---");
        customer.checkout();

        // Показываем историю заказов (новое меню)
        customer.showOrderHistory();

        // Финальная информация
        System.out.println("\n--- Финальная информация ---");
        System.out.println(customer.toString());
    }
}