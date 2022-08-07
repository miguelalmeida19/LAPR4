package eapli.base.app.backoffice.console.presentation.orders;

import eapli.base.ordermanagement.domain.domain.model.PaymentMethod;
import eapli.base.productmanagement.domain.model.Product;
import eapli.framework.visitor.Visitor;

public class PaymentMethodPrinter implements Visitor<PaymentMethod>{
    @Override
    public void visit(final PaymentMethod visitee) {
        System.out.printf("%30s",
                visitee.getPaymentMethod());
    }
}
