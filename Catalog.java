import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Catalog {
    private List<Category> baseCategories;
    private int mainCategoryCount;

    public Catalog() {
        this.baseCategories = new ArrayList<>();
        this.mainCategoryCount = 0;
    }

    // добавить главную категорию
    public void addBaseCategory(Category category) {
        baseCategories.add(category);
        mainCategoryCount++;
    }

    public List<Category> getBaseCategories() {
        return baseCategories;
    }

    public int getMainCategoryCount() {
        return mainCategoryCount;
    }

    // показать все главные категории (используем StreamAPI)
    public void showCatalog() {
        System.out.println("Каталог:");
        baseCategories.stream()
                .map(category -> "- " + category.getName())
                .forEach(System.out::println);
        System.out.println("Количество главных категорий: " + mainCategoryCount);
    }

    // Получить все подкатегории через StreamAPI
    public List<Category> getAllSubCategories() {
        return baseCategories.stream()
                .flatMap(category -> category.getSubCategories().stream())
                .collect(Collectors.toList());
    }
}