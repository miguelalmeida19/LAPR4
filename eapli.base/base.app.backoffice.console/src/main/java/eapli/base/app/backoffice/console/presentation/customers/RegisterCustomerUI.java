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
package eapli.base.app.backoffice.console.presentation.customers;

import eapli.base.customermanagement.application.AddCustomerController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;


public class RegisterCustomerUI extends AbstractUI {

    private final AddCustomerController theController = new AddCustomerController();

    private final String SEPARATOR = "___";

    @Override
    protected boolean doShow() {
        final String firstName = Console.readLine("First Name");
        final String lastName = Console.readLine("Last Name");
        final String vat = Console.readLine("Vat");
        final String email = Console.readLine("E-Mail");
        final String phoneNumber = Console.readLine("Phone Number");

        //Addresses ----------------------------------
        final String streetName = Console.readLine("Street Name");
        final String doorNumber = Console.readLine("Door Number");
        final String postalCode = Console.readLine("Postal Code");
        final String city = Console.readLine("City");
        final String country = Console.readLine("Country");
        final String addresses = streetName + SEPARATOR +
                doorNumber + SEPARATOR +
                postalCode + SEPARATOR +
                city + SEPARATOR +
                country;
        //--------------------------------------------

        final String gender = Console.readLine("Gender");
        final String birthdate = Console.readLine("Birthdate (dd-MM-yyyy)");

        try {
            this.theController.addCustomer(firstName, lastName, vat, email, phoneNumber, addresses, gender, birthdate);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That customerId is already in use.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Add Customer";
    }
}
