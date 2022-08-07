package eapli.base.app.backoffice.console.presentation.orders;

import eapli.base.customermanagement.application.CustomerManagementService;
import eapli.base.customermanagement.application.CustomerRegistry;
import eapli.base.ordermanagement.domain.domain.application.OrderManagementService;
import eapli.base.ordermanagement.domain.domain.application.OrderRegistry;
import eapli.base.ordermanagement.domain.domain.model.OrderDto;
import eapli.base.spomsp.SPOMSP;
import eapli.base.spomsp.services.OrdersService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckStatusOfOrdersController {

    private final OrdersService ordersService = new OrdersService();
    private final CustomerManagementService service = CustomerRegistry.customerService();
    private final OrderManagementService orderManagementService = OrderRegistry.orderService();
    private final static AuthorizationService authz = AuthzRegistry.authorizationService();
    public Object getCustomerOrdersStatus(){
        List<OrderDto> result = new ArrayList<>();
        try {
            String username = authz.session().get().authenticatedUser().username().toString();
            result = (List<OrderDto>) ordersService.sendMessageToServer(SPOMSP.Code.CUSTOMER_ORDERS.code, username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
