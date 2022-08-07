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
package eapli.base.app.backoffice.console.presentation.warehouses;

import eapli.base.warehousemanagement.application.ListRowsController;
import eapli.base.warehousemanagement.domain.model.AisleRow;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

@SuppressWarnings({ "squid:S106" })
public class ListRowsUI extends AbstractListUI<AisleRow> {
    private ListRowsController theController = new ListRowsController();

    @Override
    public String headline() {
        return "List Rows";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    protected Iterable<AisleRow> elements() {
        return theController.allRows();
    }

    @Override
    protected Visitor<AisleRow> elementPrinter() {
        return new RowPrinter();
    }

    @Override
    protected String elementName() {
        return "AisleRow";
    }

    @Override
    protected String listHeader() {
        return String.format("   %30s%30s%30s", "ROW ID", "SHELVES");
    }
}
