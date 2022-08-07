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
package eapli.base.customermanagement.domain.model;

import javax.persistence.*;

import eapli.base.customermanagement.utils.SendEmail;
import eapli.base.ordermanagement.domain.domain.model.OrderItem;
import eapli.base.ordermanagement.domain.domain.model.ProductOrder;
import eapli.base.surveymanagement.domain.model.Survey;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer implements AggregateRoot<String> {

    @Version
    private Long version;

    @GenericGenerator(name = "cust_id", strategy = "eapli.base.customermanagement.domain.model.CustomerIdGenerator")

    @Id @GeneratedValue(generator = "cust_id")
    private String customerId;
    @Embedded
    private Email email;
    @Embedded
    private Gender gender;
    @Embedded
    private Name name;
    @Embedded
    private PhoneNumber phoneNumber;
    @Embedded
    private Vat vat;
    @Embedded
    private Addresses addresses;
    @Embedded
    private Birthdate birthDate;

    @OneToMany
    private List<ProductOrder> orders = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "survey_customer",
            joinColumns = @JoinColumn(name = "customerId"),
            inverseJoinColumns = @JoinColumn(name = "surveyCode"))
    private List<Survey> surveys = new ArrayList<>();

    private boolean active;

    @OneToOne()
    private SystemUser systemUser;

    public Customer(final SystemUser user, final Email email, final Gender gender, final Name name, final Vat vat, final PhoneNumber phoneNumber, final Addresses addresses, final Birthdate birthDate) {
        if (user == null || email == null || name == null || vat == null || phoneNumber == null || birthDate == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.vat = vat;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
        this.birthDate = birthDate;
        SendEmail.sendWelcomeEmail(email.toString());
    }

    public Customer(final Email email, final Gender gender, final Name name, final Vat vat, final PhoneNumber phoneNumber, final Addresses addresses, final Birthdate birthDate, final SystemUser user) {
        if (email == null || name == null || vat == null || phoneNumber == null || birthDate == null) {
            throw new IllegalArgumentException();
        }
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.vat = vat;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
        this.birthDate = birthDate;
        this.systemUser = user;
        SendEmail.sendWelcomeEmail(email.toString());
    }

    protected Customer() {
        // for ORM only
    }

    public SystemUser user() {
        return this.systemUser;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public String identity() {
        return this.customerId;
    }

    public Name name() {
        return customerName();
    }

    private Name customerName() {
        return this.name;
    }

    public Email email() {
        return customerEmail();
    }

    private Email customerEmail() {
        return this.email;
    }

    public Gender gender() {
        return customerGender();
    }

    private Gender customerGender() {
        return this.gender;
    }

    public SystemUser systemUser() {
        return customerUser();
    }

    private SystemUser customerUser() {
        return this.systemUser;
    }

    public PhoneNumber phoneNumber() {
        return customerPhoneNumber();
    }

    private PhoneNumber customerPhoneNumber() {
        return this.phoneNumber;
    }

    public Vat vat() {
        return customerVat();
    }

    private Vat customerVat() {
        return this.vat;
    }

    public Addresses addresses() {
        return customerAddresses();
    }

    private Addresses customerAddresses() {
        return this.addresses;
    }

    public Birthdate birthDate() {
        return customerBirthDate();
    }

    private Birthdate customerBirthDate() {
        return this.birthDate;
    }

    public List<Survey> surveys() {
        return surveys;
    }

    public boolean isActive() {
        return this.active;
    }

    public List<ProductOrder> orders() {
        return orders;
    }
}
