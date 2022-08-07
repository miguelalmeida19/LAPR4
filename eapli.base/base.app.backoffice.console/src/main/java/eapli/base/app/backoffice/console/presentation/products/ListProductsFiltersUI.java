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

import eapli.base.productmanagement.application.ListProductsController;
import eapli.base.productmanagement.domain.model.*;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"squid:S106"})
public class ListProductsFiltersUI extends AbstractListUI<Product> {
    private ListProductsController theController = new ListProductsController();
    private String filter;
    private String sorter;
    private String display;
    private Object value;
    private Object value1;

    private final Comparator<Product> compareByShortDescription = Comparator.comparing(Product::shortDescription);
    private final Comparator<Product> compareByLongDescription = Comparator.comparing(Product::longDescription);
    private final Comparator<Product> compareByTechnicalDescription = Comparator.comparing(Product::technicalDescription);
    private final Comparator<Product> compareByDescription = compareByShortDescription.thenComparing(compareByLongDescription).thenComparing(compareByTechnicalDescription);

    private final Comparator<Product> compareByProductId = Comparator.comparing(Product::identity);
    private final Comparator<Product> compareByBrand = Comparator.comparing(Product::brand);
    private final Comparator<Product> compareByCategory = Comparator.comparing(Product::prodCat);
    private final Comparator<Product> compareByUnitPrice = Comparator.comparing(Product::price);

    private Iterable<Product> productIterable = new ArrayList<>();

    public ListProductsFiltersUI(String filter, String sorter, String display, Object value) {
        this.filter = filter;
        this.sorter = sorter;
        this.display = display;
        this.value = value;
    }

    public ListProductsFiltersUI(String filter, String sorter, String display, Object value, Object value1) {
        this.filter = filter;
        this.sorter = sorter;
        this.display = display;
        this.value = value;
        this.value1 = value1;
    }

    @Override
    public String headline() {
        return "Product Catalog - " + filter + ": " + value.toString();
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    protected Iterable<Product> elements() {
        if (filter.equals("Category")) {
            if (value1==null){
                productIterable = sortList((List<Product>) theController.findByProductCategory((ProductCategory) value));
                return sortList((List<Product>) theController.findByProductCategory((ProductCategory) value));
            }else {
                productIterable = sortList((List<Product>) theController.findByBrandAndProductCategory((Brand) value1, (ProductCategory) value));
                return sortList((List<Product>) theController.findByBrandAndProductCategory((Brand) value1, (ProductCategory) value));
            }
        } else if (filter.equals("Description")) {
            Iterable<Product> list1 = new ArrayList<>();
            Iterable<Product> list2 = new ArrayList<>();
            Iterable<Product> list3 = new ArrayList<>();

            try {
                list1 = theController.findByLongDescription(new Longdescription((String) value));
            } catch (Exception e) {

            }
            try {
                list2 = theController.findByShortDescription(new Shortdescription((String) value));
            } catch (Exception e) {

            }
            try {
                list3 = theController.findByTechnicalDescription(new Technicaldescription((String) value));
            } catch (Exception e) {

            }
            List<Product> list4 = new ArrayList<>();
            list4.addAll((Collection<? extends Product>) list1);
            list4.addAll((Collection<? extends Product>) list2);
            list4.addAll((Collection<? extends Product>) list3);

            list4 = sortList(list4);
            productIterable = list4;
            return list4;
        } else { //Brand
            if (value1==null){
                productIterable = sortList((List<Product>) theController.findByBrand((Brand) value));
                return sortList((List<Product>) theController.findByBrand((Brand) value));
            }else {
                productIterable = sortList((List<Product>) theController.findByBrandAndProductCategory((Brand) value1, (ProductCategory) value));
                return sortList((List<Product>) theController.findByBrandAndProductCategory((Brand) value1, (ProductCategory) value));
            }
        }
    }

    public Iterable<Product> elementsList(){
        return productIterable;
    }

    @Override
    protected Visitor<Product> elementPrinter() {
        return new ProductPrinter();
    }

    @Override
    protected String elementName() {
        return "Product";
    }

    @Override
    protected String listHeader() {
        return String.format("   %30s%30s%30s%30s%30s", "PRODUCT ID", "SHORT DESCRIPTION", "BRAND", "CATEGORY", "UNIT PRICE");
    }

    private List<Product> sortList(List<Product> list){

        if (display.equals("Descendant")) {
            if (sorter.equals("By Product Id")) {
                return list.stream().sorted( compareByProductId.reversed() ).collect(Collectors.toList());
            }
            if (sorter.equals("By Description")) {
                return list.stream().sorted( compareByDescription.reversed()).collect(Collectors.toList());
            }
            if (sorter.equals("By Brand")) {
                return list.stream().sorted( compareByBrand.reversed() ).collect(Collectors.toList());
            }
            if (sorter.equals("By Category")) {
                return list.stream().sorted( compareByCategory.reversed() ).collect(Collectors.toList());
            }
            if (sorter.equals("By Unit Price")) {
                return list.stream().sorted(compareByUnitPrice.reversed() ).collect(Collectors.toList());
            }
        }else {
            if (sorter.equals("By Product Id")) {
                return list.stream().sorted( compareByProductId ).collect(Collectors.toList());
            }
            if (sorter.equals("By Description")) {
                return list.stream().sorted( compareByDescription ).collect(Collectors.toList());
            }
            if (sorter.equals("By Brand")) {
                return list.stream().sorted( compareByBrand ).collect(Collectors.toList());
            }
            if (sorter.equals("By Category")) {
                return list.stream().sorted( compareByCategory ).collect(Collectors.toList());
            }
            if (sorter.equals("By Unit Price")) {
                return list.stream().sorted(compareByUnitPrice ).collect(Collectors.toList());
            }
        }
        return null;
    }
}
