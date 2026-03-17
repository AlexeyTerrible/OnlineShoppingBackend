public class Product {
   private String title = "";
   private float price = 0.0F;

   public String getTitle() {
      return this.title;
   }

   public float getPrice() {
      return this.price;
   }

   public void setTitle(String var1) {
      this.title = var1;
   }

   public void setPrice(float var1) {
      this.price = var1;
   }

   public Product(String var1, float var2) {
      this.title = var1;
      this.price = var2;
   }

   public Product() {
   }
}
