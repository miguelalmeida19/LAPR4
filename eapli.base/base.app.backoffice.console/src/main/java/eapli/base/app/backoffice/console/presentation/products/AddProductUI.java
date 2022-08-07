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
package eapli.base.app.backoffice.console.presentation.products;

import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.warehouses.AislePrinter;
import eapli.base.app.backoffice.console.presentation.warehouses.RowPrinter;
import eapli.base.productmanagement.application.AddProductController;
import eapli.base.productmanagement.application.ListProductCategoriesController;
import eapli.base.productmanagement.domain.model.ProductCategory;
import eapli.base.warehousemanagement.application.ListAislesController;
import eapli.base.warehousemanagement.application.ListRowsController;
import eapli.base.warehousemanagement.domain.model.*;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AddProductUI extends AbstractUI {

    private final AddProductController theController = new AddProductController();
    private final ListProductCategoriesController theController1 = new ListProductCategoriesController();
    private final ListAislesController theController2 = new ListAislesController();
    private final ListRowsController theController3 = new ListRowsController();

    @Override
    protected boolean doShow() {
        final String price = Console.readLine("Price");
        final String brand = Console.readLine("Brand");
        final String reference = Console.readLine("Reference");
        final String shortDescription = Console.readLine("Short Description");
        final String longDescription = Console.readLine("Long Description");
        final String technicalDescription = Console.readLine("Technical Description");
        final String barcode = Console.readLine("Barcode");
        final String width = Console.readLine("Width");
        final String length = Console.readLine("Length");
        final String height = Console.readLine("Height");
        final String weight = Console.readLine("Weight");

        final Iterable<ProductCategory> productCategories = theController1.allProductCategories();
        final SelectWidget<ProductCategory> selector = new SelectWidget<>("Select a Product Category", productCategories, new ProductCategoryPrinter());
        selector.show();

        final ProductCategory productCategory = selector.selectedElement();
        File photoFile = selectFile();

        final Iterable<Aisles> aisles = theController2.allAisles();
        final SelectWidget<Aisles> selector2 = new SelectWidget<>("Select an Aisle", aisles, new AislePrinter());
        selector2.show();
        final AisleId aisleId = selector2.selectedElement().identity();

        final Iterable<AisleRow> rows = selector2.selectedElement().rows();
        final SelectWidget<AisleRow> selector3 = new SelectWidget<>("Select a AisleRow", rows, new RowPrinter());
        selector3.show();
        final RowsId rowsId = selector3.selectedElement().identity();

        Long shelf = Long.valueOf(0);
        List<Long> shelves = new ArrayList<>();
        for (int j = 1; j <= Integer.parseInt(selector3.selectedElement().shelf().toString()); j++) {
            shelves.add((long) j);
        }

        showShelves(shelves);
        while (!shelves.contains(shelf)) {
            shelf = Console.readLong("Choose a shelf");
        }

        try {
            this.theController.addProduct(price, brand, reference, shortDescription, longDescription,
                    technicalDescription, barcode, width, length, height, weight, productCategory, photoFile, aisleId, rowsId, shelf);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That productId is already in use.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Add Product";
    }

    private File selectFile() {
        String result = Application.settings().getPhotoSelection();

        if (result.equals("Window")){
            JFrame newFrame = new JFrame();
            newFrame.setVisible(true);
            newFrame.setAlwaysOnTop(false);

            FileDialog fd = new FileDialog(newFrame, "Select the Photo", FileDialog.LOAD);
            fd.setAlwaysOnTop(true);
            fd.setDirectory("C:\\");
            fd.setFile("*.jpg;*.jpeg;*.png");
            fd.setMultipleMode(false);
            fd.setVisible(true);
            String path = fd.getDirectory() + fd.getFile();
            if (path.equals("nullnull")) {
                newFrame.setVisible(false);
                return selectFile();
            } else {
                newFrame.setVisible(false);
                return new File(path);
            }
        }else {
            return new File(Console.readLine("Type the photo path"));
        }
    }

    private void showShelves(List<Long> shelves) {
        int counter = 1;
        for (Long l : shelves) {
            System.out.println("[" + counter + "] " + "Shelf " + counter + ": " + l);
            counter++;
        }
    }
}
