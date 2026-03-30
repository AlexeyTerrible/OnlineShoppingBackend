import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Category> categories;

    public Category(String name) {
        this.name = name;
        this.categories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void showCategory() {
        System.out.println("Категория: " + name);

        if (categories.isEmpty()) {
            System.out.println("Подкатегорий нет");
        } else {
            System.out.println("Подкатегории:");
            for (Category category : categories) {
                System.out.println("- " + category.getName());
            }
        }
    }
}