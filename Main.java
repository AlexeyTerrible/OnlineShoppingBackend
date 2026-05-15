import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Инициализация категорий
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

        // FACTORY PATTERN - создаем продукты через фабрику
        Electronics macbook = (Electronics) Product.create("Electronics", "Macbook pro", 2599.0f, 24);
        Food apple = (Food) Product.create("Food", "Яблоко", 2.5f, 52);
        Electronics iphone = (Electronics) Product.create("Electronics", "Iphone 17", 799.0f, 12);
        Food bread = (Food) Product.create("Food", "Хлеб", 1.2f, 150);
        Food banana = (Food) Product.create("Food", "Банан", 1.8f, 95);
        Electronics headphones = (Electronics) Product.create("Electronics", "Наушники Sony", 149.0f, 12);

        // Создаем покупателя
        Customer customer = new Customer("Иван", "Петров", 1990, 1000.0);

        // Добавляем начальные товары в корзину
        customer.addProduct(apple);
        customer.addProduct(bread);
        customer.addProduct(iphone);
        customer.addProduct(banana);

        boolean running = true;

        while (running) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("ГЛАВНОЕ МЕНЮ");
            System.out.println("=".repeat(50));
            System.out.println("1. Показать каталог");
            System.out.println("2. Показать корзину");
            System.out.println("3. Добавить товар в корзину");
            System.out.println("4. Оформить заказ");
            System.out.println("5. Показать историю заказов");
            System.out.println("6. Статистика цен всех товаров");
            System.out.println("7. Поиск товаров");
            System.out.println("8. Фильтрация товаров по цене");
            System.out.println("9. Показать доступность товаров");
            System.out.println("10. Информация о покупателе");
            System.out.println("11. Показать корзину со скидкой 10% (Strategy demo)");
            System.out.println("0. Выйти из программы");
            System.out.println("=".repeat(50));
            System.out.print("Ваш выбор: ");

            // Проверяем, есть ли ввод
            if (!scanner.hasNextInt()) {
                System.out.println("\nОшибка: введите число от 0 до 11");
                scanner.nextLine(); // очистка некорректного ввода
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера после числа

            try {
                switch (choice) {
                    case 1:
                        System.out.println("\n=== КАТАЛОГ ===");
                        catalog.showCatalog();
                        System.out.println("\n--- Electronics ---");
                        electronics.showCategory();
                        System.out.println("\n--- Food ---");
                        food.showCategory();
                        System.out.println("\n--- Clothing ---");
                        clothing.showCategory();
                        break;

                    case 2:
                        System.out.println("\n=== КОРЗИНА ===");
                        customer.showCart();
                        customer.showCartStatistics();
                        break;

                    case 3:
                        System.out.println("\n=== ДОБАВЛЕНИЕ ТОВАРА ===");
                        System.out.println("Доступные товары:");
                        System.out.println("1. Macbook pro - 2599.0 руб.");
                        System.out.println("2. Яблоко - 2.5 руб.");
                        System.out.println("3. Iphone 17 - 799.0 руб.");
                        System.out.println("4. Хлеб - 1.2 руб.");
                        System.out.println("5. Банан - 1.8 руб.");
                        System.out.println("6. Наушники Sony - 149.0 руб.");
                        System.out.print("Выберите товар (1-6): ");

                        if (!scanner.hasNextInt()) {
                            System.out.println("Неверный выбор! Введите число от 1 до 6");
                            scanner.nextLine();
                            break;
                        }

                        int productChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (productChoice) {
                            case 1: customer.addProduct(macbook); break;
                            case 2: customer.addProduct(apple); break;
                            case 3: customer.addProduct(iphone); break;
                            case 4: customer.addProduct(bread); break;
                            case 5: customer.addProduct(banana); break;
                            case 6: customer.addProduct(headphones); break;
                            default: System.out.println("Неверный выбор!");
                        }
                        break;

                    case 4:
                        System.out.println("\n=== ОФОРМЛЕНИЕ ЗАКАЗА ===");
                        customer.checkout();
                        break;

                    case 5:
                        System.out.println("\n=== ИСТОРИЯ ЗАКАЗОВ ===");
                        customer.showOrderHistory();
                        break;

                    case 6:
                        System.out.println("\n=== СТАТИСТИКА ЦЕН ===");
                        Product.showPriceStatistics();
                        break;

                    case 7:
                        System.out.println("\n=== ПОИСК ТОВАРОВ ===");
                        System.out.print("Введите название для поиска: ");
                        String searchTerm = scanner.nextLine();
                        customer.searchProducts(searchTerm);
                        break;

                    case 8:
                        System.out.println("\n=== ФИЛЬТРАЦИЯ ПО ЦЕНЕ ===");
                        System.out.print("Введите максимальную цену: ");
                        if (!scanner.hasNextDouble()) {
                            System.out.println("Ошибка: введите число");
                            scanner.nextLine();
                            break;
                        }
                        double maxPrice = scanner.nextDouble();
                        scanner.nextLine();
                        customer.showFilteredProducts(maxPrice);
                        break;

                    case 9:
                        System.out.println("\n=== ДОСТУПНОСТЬ ТОВАРОВ ===");
                        customer.showProductAvailability();
                        break;

                    case 10:
                        System.out.println("\n=== ИНФОРМАЦИЯ О ПОКУПАТЕЛЕ ===");
                        System.out.println(customer.toString());
                        break;

                    case 11:
                        System.out.println("\n=== КОРЗИНА СО СКИДКОЙ 10% (Strategy Pattern) ===");
                        customer.showCart();
                        System.out.println("\nОбычная цена: $" + customer.getCartTotal());

                        double discountedTotal = customer.getCartTotal(products ->
                                products.stream()
                                        .mapToDouble(Product::getPrice)
                                        .sum() * 0.9
                        );
                        System.out.println("Цена со скидкой 10%: $" + String.format("%.2f", discountedTotal));
                        break;

                    case 0:
                        System.out.println("\nСпасибо за использование программы! До свидания!");
                        running = false;
                        break;

                    default:
                        System.out.println("\nНеверный выбор! Пожалуйста, выберите пункт от 0 до 11.");
                }
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                scanner.nextLine(); // очистка буфера при ошибке
            }
        }

        // Безопасное закрытие сканера
        try {
            scanner.close();
        } catch (Exception e) {
            // Игнорируем ошибки при закрытии
        }

        System.out.println("Программа завершена.");
        System.exit(0); // Явное завершение программы
    }
}