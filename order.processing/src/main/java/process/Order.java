package process;

import products.Product;

public record Order<T extends Product>(T product) {
}
