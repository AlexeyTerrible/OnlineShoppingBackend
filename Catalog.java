import java.util.ArrayList;
import java.util.List;


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

    // показать все главные категории
    public void showCatalog() {
        System.out.println("Каталог:");
        for (Category category : baseCategories) {
            System.out.println("- " + category.getName());
        }
        System.out.println("Количество главных категорий: " + mainCategoryCount);
    }
}


