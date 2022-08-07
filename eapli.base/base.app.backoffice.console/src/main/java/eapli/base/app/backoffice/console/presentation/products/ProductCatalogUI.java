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

import eapli.base.productmanagement.application.ListProductCategoriesController;
import eapli.base.productmanagement.domain.model.*;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProductCatalogUI extends AbstractUI {

    private List<String> filters = new ArrayList<>();
    private List<String> askMore = new ArrayList<>();
    private List<String> sorters = new ArrayList<>();
    private List<String> displayers = new ArrayList<>();
    private final ListProductCategoriesController listProductCategoriesController = new ListProductCategoriesController();
    int option = -1;
    int askMoreOption = -1;
    String filter = "";
    String askMoreFilter = "";
    String sorter = "";
    String display = "";

    @Override
    protected boolean doShow() {
        option = -1;
        askMoreOption = -1;
        filter = "";
        sorter = "";
        display = "";

        try {
            ListProductsFiltersUI listProductsFiltersUI = showCatalog();
            listProductsFiltersUI.show();
        } catch (final Exception e) {
            System.out.println("No matching for your search.");
            ProductCatalogUI productCatalogUI = new ProductCatalogUI();
            return productCatalogUI.doShow();
        }
        ProductCatalogUI productCatalogUI = new ProductCatalogUI();
        return productCatalogUI.doShow();
    }

    @Override
    public String headline() {
        return "Product Catalog";
    }

    private void showFilters() {
        filters = Arrays.asList(new String[]{"Category", "Description", "Brand"});

        int counter = 1;
        for (String l : filters) {
            System.out.println("[" + counter + "] " + l);
            counter++;
        }
    }

    private void askMore() {
        askMoreOption = -1;
        askMore = Arrays.asList(new String[]{"Yes", "No"});

        int counter = 1;
        for (String l : askMore) {
            System.out.println("[" + counter + "] " + l);
            counter++;
        }
    }

    private void showSorters() {
        sorters = Arrays.asList(new String[]{"By Product Id", "By Description", "By Brand", "By Category", "By Unit Price"});

        int counter = 1;
        for (String l : sorters) {
            System.out.println("[" + counter + "] " + l);
            counter++;
        }
    }

    private void showDisplayers() {
        displayers = Arrays.asList(new String[]{"Descendant", "Ascendant"});

        int counter = 1;
        for (String l : displayers) {
            System.out.println("[" + counter + "] " + l);
            counter++;
        }
    }

    private void getSortInfo(){
        option=-1;
        while (option<1 || option>sorters.size()){
            showSorters();
            option = Console.readInteger("Select how do you want to sort the results");
        }

        sorter = sorters.get(option-1);

        while (option<1 || option>displayers.size()){
            showDisplayers();
            option = Console.readInteger("Select the direction of results sort");
        }

        display = displayers.get(option-1);
    }

    private void getMoreFilters(){
        while (askMoreOption<1 || askMoreOption>askMore.size()){
            askMore();
            askMoreOption = Console.readInteger("Do you want to add another filter?");
        }

        askMoreFilter = askMore.get(askMoreOption-1);
    }

    private ProductCategory selectProductCategory(){
        final Iterable<ProductCategory> categories = listProductCategoriesController.allProductCategories();
        final SelectWidget<ProductCategory> selector = new SelectWidget<>("Select a Category", categories, new ProductCategoryPrinter());
        selector.show();
        return selector.selectedElement();
    }

    private String selectBrand(){
        return Console.readLine("Type the brand name");
    }

    private String selectDescription(){
        return Console.readLine("Type your search term");
    }

    private void selectFilter(){
        while (option<1 || option>filters.size()){
            showFilters();
            option = Console.readInteger("Select filter");
        }

        filter = filters.get(option-1);

    }

    private ListProductsFiltersUI listCategoryBrand(ProductCategory productCategory, String search){
        return new ListProductsFiltersUI(filter, sorter, display, productCategory, new Brand(search));
    }

    private ListProductsFiltersUI listBrand(String brand){
        return new ListProductsFiltersUI(filter, sorter, display, new Brand(brand));
    }

    private ListProductsFiltersUI listCategory(ProductCategory productCategory){
        return new ListProductsFiltersUI(filter, sorter, display, productCategory);
    }

    private ListProductsFiltersUI listDescription(String description){
        return new ListProductsFiltersUI(filter, sorter, display, description);
    }

    public ListProductsFiltersUI showCatalog(){
        selectFilter();
        if (filter.equals("Category")){
            final ProductCategory productCategory = selectProductCategory();
            getMoreFilters();
            if (askMoreFilter.equals("Yes")){
                String search = selectBrand();
                getSortInfo();
                return listCategoryBrand(productCategory, search);
            }else {
                getSortInfo();
                return listCategory(productCategory);
            }
        }
        if (filter.equals("Description")){
            String search = selectDescription();
            getSortInfo();
            return listDescription(search);
        }
        if (filter.equals("Brand")){
            String search = selectBrand();
            getMoreFilters();
            if (askMoreFilter.equals("Yes")){
                final ProductCategory productCategory = selectProductCategory();
                getSortInfo();
                listCategoryBrand(productCategory, search);
            }else {
                getSortInfo();
                listBrand(search);
            }
        }
        return null;
    }
}
