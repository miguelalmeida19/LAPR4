package eapli.base.ordermanagement.domain.domain.application;

import eapli.base.ordermanagement.domain.domain.repositories.OrderRepository;
import eapli.framework.validations.Invariants;

public final class OrderRegistry {

    private static OrderManagementService orderManagementService;

    private OrderRegistry() {
    }

    public static void configure(final OrderRepository orderRepository) {
        orderManagementService = new OrderManagementService(orderRepository);
    }

    public static OrderManagementService orderService() {
        Invariants.nonNull(orderManagementService);
        return orderManagementService;
    }
}