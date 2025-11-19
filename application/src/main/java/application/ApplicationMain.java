package application;

import factory.ProductFactory;
import multithreading.AsyncExecutor;
import order.Order;
import process.OrderProcessor;
import repository.OrderRepository;

public class ApplicationMain {
    private final static ProductFactory PRODUCT_FACTORY = new ProductFactory();
    private final static OrderRepository ORDER_REPOSITORY = new OrderRepository();
    private final static AsyncExecutor ASYNC_EXECUTOR = new AsyncExecutor();

    public static void main(final String[] args) {
        // Generate products and store then as orders.
        for (int i = 0; i < 100; i++) {
            final var product = i % 2 == 0
                    ? PRODUCT_FACTORY.createElectronics()
                    : PRODUCT_FACTORY.createClothingArticle();

            ORDER_REPOSITORY.saveProduct(Order.builder()
                    .id(Integer.toString(i))
                    .product(product)
                    .build());
        }

        ORDER_REPOSITORY.findAllProducts()
                .stream()
                .map(OrderProcessor::new)
                .forEach(orderProcessor -> ASYNC_EXECUTOR.execute(orderProcessor::processOrder));

    }
}
