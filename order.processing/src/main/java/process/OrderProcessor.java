package process;

public class OrderProcessor {
    private final Order<?> product;

    public OrderProcessor(final Order<?> product) {
        this.product = product;
    }

    public void processOrder() {
        System.out.printf("Starting processing of the order for product ID: %s...%n", product.product().getId());

        try {
            // Simulate order processing time.
            Thread.sleep(500L);
        } catch (final InterruptedException e) {
            System.err.printf("Order processing was interrupted: %s%n", e.getMessage());
        }

        System.out.printf("Processing finished of the order for product ID: %s%n", product.product().getId());
    }
}
