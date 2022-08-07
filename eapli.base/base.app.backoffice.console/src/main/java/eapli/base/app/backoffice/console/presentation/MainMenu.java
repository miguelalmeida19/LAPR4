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
package eapli.base.app.backoffice.console.presentation;

import eapli.base.app.backoffice.console.presentation.AGV.AddAgvUI;
import eapli.base.app.backoffice.console.presentation.dashboard.ShowDashboardUI;
import eapli.base.app.backoffice.console.presentation.orders.AddOrderUI;
import eapli.base.app.backoffice.console.presentation.orders.DeliveredOrdersUI;
import eapli.base.app.backoffice.console.presentation.orders.ListOrdersAction;
import eapli.base.app.backoffice.console.presentation.orders.UpdateOrderStatusUI;
import eapli.base.app.backoffice.console.presentation.products.*;
import eapli.base.app.backoffice.console.presentation.customers.ListCustomersAction;
import eapli.base.app.backoffice.console.presentation.customers.RegisterCustomerUI;
import eapli.base.app.backoffice.console.presentation.surveys.CreateSurveyUI;
import eapli.base.app.backoffice.console.presentation.surveys.StatisticalReportUI;
import eapli.base.app.backoffice.console.presentation.warehouses.AssignOrderAgvUI;
import eapli.base.app.backoffice.console.presentation.warehouses.ListAislesAction;
import eapli.base.app.backoffice.console.presentation.warehouses.UploadWarehousePlantUI;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // CUSTOMERS
    private static final int ADD_CUSTOMER_OPTION = 1;
    private static final int LIST_CUSTOMERS_OPTION = 2;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;

    // DISH TYPES
    private static final int DISH_TYPE_REGISTER_OPTION = 1;
    private static final int DISH_TYPE_LIST_OPTION = 2;
    private static final int DISH_TYPE_CHANGE_OPTION = 3;
    private static final int DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION = 4;

    // DISHES
    private static final int DISH_REGISTER_OPTION = 5;
    private static final int DISH_LIST_OPTION = 6;
    private static final int DISH_REGISTER_DTO_OPTION = 7;
    private static final int DISH_LIST_DTO_OPTION = 8;
    private static final int DISH_ACTIVATE_DEACTIVATE_OPTION = 9;
    private static final int DISH_CHANGE_OPTION = 10;

    // DISH PROPERTIES
    private static final int CHANGE_DISH_NUTRICIONAL_INFO_OPTION = 1;
    private static final int CHANGE_DISH_PRICE_OPTION = 2;

    // MATERIALS
    private static final int MATERIAL_REGISTER_OPTION = 1;
    private static final int MATERIAL_LIST_OPTION = 2;

    // REPORTING
    private static final int REPORTING_DISHES_PER_DISHTYPE_OPTION = 1;
    private static final int REPORTING_HIGH_CALORIES_DISHES_OPTION = 2;
    private static final int REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION = 3;

    // MEALS
    private static final int LIST_MEALS_OPTION = 1;
    private static final int MEAL_REGISTER_OPTION = 2;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int SETTINGS_OPTION = 4;
    private static final int DISH_OPTION = 5;
    private static final int TRACEABILITY_OPTION = 6;
    private static final int MEALS_OPTION = 7;
    private static final int REPORTING_DISHES_OPTION = 8;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.POWER_USER, BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.SALES_CLERK)) {
            final Menu customersMenu = buildSalesRegisterCustomer();
            mainMenu.addSubMenu(USERS_OPTION, customersMenu);
            final Menu productsMenu = buildSalesProductsManagement();
            mainMenu.addSubMenu(DEACTIVATE_USER_OPTION, productsMenu);
            final Menu ordersMenu = buildSalesOrdersManagement();
            mainMenu.addSubMenu(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, ordersMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.WAREHOUSE_EMPLOYEE)) {
            final Menu warehouseEmployeeMenu = buildWarehouseEmployeeMenu();
            mainMenu.addSubMenu(2, warehouseEmployeeMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.SALES_MANAGER)) {
            final Menu surveyManagementMenu = buildSurveyMenu();
            mainMenu.addSubMenu(2, surveyManagementMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildSalesRegisterCustomer() {
        final Menu menu = new Menu("Customers >");

        RegisterCustomerUI registerCustomerUI = new RegisterCustomerUI();
        menu.addItem(ADD_CUSTOMER_OPTION, "Register Customer", new RegisterCustomerUI()::show);
        menu.addItem(LIST_CUSTOMERS_OPTION, "List all Customers", new ListCustomersAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildSalesProductsManagement() {
        final Menu menu = new Menu("Products >");

        AddProductUI addProductUI = new AddProductUI();
        menu.addItem(ADD_CUSTOMER_OPTION, "Add Product", new AddProductUI()::show);
        menu.addItem(LIST_CUSTOMERS_OPTION, "List all Products", new ListProductsAction());

        AddProductCategoryUI addProductCategoryUI = new AddProductCategoryUI();
        menu.addItem(DISH_TYPE_CHANGE_OPTION, "Add Product Category", new AddProductCategoryUI()::show);
        menu.addItem(DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION, "List all Product Categories", new ListProductCategoriesAction());

        menu.addItem(DISH_REGISTER_OPTION, "View Product Catalog", new ProductCatalogUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildSalesOrdersManagement() {
        final Menu menu = new Menu("Orders >");

        AddOrderUI addOrderUI = new AddOrderUI();
        menu.addItem(ADD_CUSTOMER_OPTION, "Add ProductOrder", new AddOrderUI()::show);
        menu.addItem(LIST_CUSTOMERS_OPTION, "List all Orders", new ListOrdersAction());
        menu.addItem(3, "Set Order Status to Delivered", new DeliveredOrdersUI()::show);

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildWarehouseEmployeeMenu() {
        final Menu menu = new Menu("Warehouse management >");

        UploadWarehousePlantUI uploadWarehousePlantUI = new UploadWarehousePlantUI();
        menu.addItem(ADD_CUSTOMER_OPTION, "Upload Warehouse Plant", new UploadWarehousePlantUI()::show);
        menu.addItem(LIST_CUSTOMERS_OPTION, "List all Aisles", new ListAislesAction());
        menu.addItem(3, "Add AGV", new AddAgvUI()::show);
        menu.addItem(4, "Force an Order to be prepared", new AssignOrderAgvUI()::show);
        menu.addItem(5, "Update Order Status", new UpdateOrderStatusUI()::show);
        menu.addItem(6, "Show Dashboard", new ShowDashboardUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildSurveyMenu() {
        final Menu menu = new Menu("Survey management >");
        menu.addItem(1, "Create AGV and Validate", new CreateSurveyUI()::show);
        menu.addItem(2, "Check statistical report of a survey", new StatisticalReportUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        return menu;
    }

}
