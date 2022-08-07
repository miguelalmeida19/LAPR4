package eapli.base.ordermanagement.domain.domain.application;

import eapli.base.ordermanagement.domain.domain.repositories.OrderItemRepository;
import eapli.framework.validations.Invariants;

public final class OrderItemRegistry {

    private static OrderItemManagementService orderItemManagementService;

    private OrderItemRegistry() {
    }

    public static void configure(final OrderItemRepository orderItemRepository) {
        orderItemManagementService = new OrderItemManagementService(orderItemRepository);
    }

    public static OrderItemManagementService orderItemService() {
        Invariants.nonNull(orderItemManagementService);
        return orderItemManagementService;
    }
}