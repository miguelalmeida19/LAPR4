/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation.orders;

import eapli.base.ordermanagement.domain.domain.application.ListOrderItemsController;
import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "squid:S106" })
public class ListOrderItemsForOrderUI extends AbstractListUI<OrderItem> {
    private ListOrderItemsController theController = new ListOrderItemsController();
    private List<OrderItem> orderItems = new ArrayList<>();
    private String country;

    public ListOrderItemsForOrderUI(String country, List<OrderItem> orderItemList){
        this.country = country;
        this.orderItems = orderItemList;
    }

    @Override
    public String headline() {
        return "Order Items";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    protected Iterable<OrderItem> elements() {
        return orderItems;
    }

    @Override
    protected Visitor<OrderItem> elementPrinter() {
        return new OrderItemPrinter(country);
    }

    @Override
    protected String elementName() {
        return "ProductOrder";
    }

    @Override
    protected String listHeader() {
        return String.format("   %30s%30s%30s%30s%30s", "ITEM ID", "QUANTITY", "UNITARY PRICE", "TOTAL PRICE", "TOTAL PRICE WITH TAX");
    }
}
