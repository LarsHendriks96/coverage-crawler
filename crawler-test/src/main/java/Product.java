import java.util.Objects;

public class Product {
    private final String name;
    private final Double price;
    private final Integer quantity;
    private final Category category;
    private final Boolean paid;

    // Private constructor to prevent direct instantiation
    private Product(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.category = builder.category;
        this.paid = builder.paid;
    }

    private Product() {
        this.name = null;
        this.price = 0.0;
        this.quantity = 0;
        this.category = null;
        this.paid = false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity, category, paid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Objects.equals(quantity, product.quantity) &&
                paid == product.paid &&
                Objects.equals(name, product.name) &&
                Objects.equals(category, product.category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name=" + name +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category=" + category +
                ", paid=" + paid +
                '}';
    }

    // Getters
    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Category getCategory() {
        return category;
    }

    public Boolean isPaid() {
        return paid;
    }

    // Builder class
    public static class Builder {
        private String name;
        private Double price;
        private Integer quantity;
        private Category category;
        private Boolean paid;

        public Builder() {
            // Set default values if needed
            this.quantity = 1;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder paid(Boolean paid) {
            this.paid = paid;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

    // Nested object Category
    public static class Category {
        private final String name;

        public Category(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
