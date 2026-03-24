
public class Main {
    public static void main(String[] args) {
        Product p1 = new Electronics("Ноутбук", 2500.0f, 24);
        Product p2 = new Food("Яблоко", 2.5f, 52);
/* 
        map.put("Electronics", new ArrayList<>());
        map.put("Food", new ArrayList<>());

        map.get("Electronics").add(p1);
        map.get("Food").add(p2);

        for (String key : map.keySet()) {
            System.out.println("Категория: " + key);
            for (Product product : map.get(key)) {
                product.showInfo();
            }
            System.out.println();
        }
    }
        */
}
}