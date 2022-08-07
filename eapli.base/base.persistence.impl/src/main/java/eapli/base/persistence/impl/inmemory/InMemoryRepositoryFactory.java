/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.persistence.impl.inmemory;

import eapli.base.agvmanagement.domain.repositories.AGVRepository;
import eapli.base.agvmanagement.domain.repositories.AgvModelRepository;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.customermanagement.domain.repositories.CustomerRepository;
import eapli.base.infrastructure.bootstrapers.BaseBootstrapper;
import eapli.base.infrastructure.persistence.RepositoryFactory;
import eapli.base.ordermanagement.domain.domain.repositories.OrderItemRepository;
import eapli.base.ordermanagement.domain.domain.repositories.OrderRepository;
import eapli.base.productmanagement.domain.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.domain.repositories.ProductRepository;
import eapli.base.surveymanagement.domain.repositories.SurveyAnswerRepository;
import eapli.base.surveymanagement.domain.repositories.SurveyRepository;
import eapli.base.warehousemanagement.domain.repositories.*;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.InMemoryUserRepository;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new BaseBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public ProductRepository products(final TransactionalContext tx) {
        return new InMemoryProductRepository();
    }

    @Override
    public ProductRepository products() {
        return products(null);
    }

    @Override
    public CustomerRepository customers() {
        return customers(null);
    }

    @Override
    public CustomerRepository customers(final TransactionalContext tx) {
        return new InMemoryCustomerRepository();
    }

    @Override
    public OrderRepository orders() {
        return orders(null);
    }

    @Override
    public OrderRepository orders(final TransactionalContext tx) {
        return new InMemoryOrderRepository();
    }

    @Override
    public OrderItemRepository orderItems() {
        return orderItems(null);
    }

    @Override
    public OrderItemRepository orderItems(final TransactionalContext tx) {
        return new InMemoryOrderItemRepository();
    }

    @Override
    public AgvModelRepository agvModels() {
        return agvModels(null);
    }

    @Override
    public AgvModelRepository agvModels(TransactionalContext tx) {
        return new InMemoryAgvModelRepository();
    }

    @Override
    public ProductCategoryRepository productCategories() {
        return productCategories(null);
    }

    @Override
    public ProductCategoryRepository productCategories(final TransactionalContext tx) {
        return new InMemoryProductCategoryRepository();
    }

    @Override
    public WarehouseRepository warehouses() {
        return warehouses(null);
    }

    @Override
    public WarehouseRepository warehouses(TransactionalContext tx) {
        return new InMemoryWarehouseRepository();
    }

    @Override
    public SquareRepository squares() {
        return squares(null);
    }

    @Override
    public SquareRepository squares(TransactionalContext tx) {
        return new InMemorySquaresRepository();
    }

    @Override
    public AisleRepository aisles() {
        return aisles(null);
    }

    @Override
    public AisleRepository aisles(TransactionalContext tx) {
        return new InMemoryAislesRepository();
    }

    @Override
    public RowRepository rows() {
        return rows(null);
    }

    @Override
    public RowRepository rows(TransactionalContext tx) {
        return new InMemoryRowsRepository();
    }

    @Override
    public AGVDockRepository agvDocks() {
        return agvDocks(null);
    }

    @Override
    public AGVDockRepository agvDocks(TransactionalContext tx) {
        return new InMemoryAGVDockRepository();
    }

    @Override
    public SurveyRepository surveys() {
        return surveys(null);
    }

    @Override
    public SurveyRepository surveys(TransactionalContext tx) {
        return new InMemorySurveyRepository();
    }

    @Override
    public SurveyAnswerRepository surveyAnswers() {
        return surveyAnswers(null);
    }

    @Override
    public SurveyAnswerRepository surveyAnswers(TransactionalContext tx) {
        return new InMemorySurveyAnswerRepository();
    }


    @Override
    public ClientUserRepository clientUsers(final TransactionalContext tx) {

        return new InMemoryClientUserRepository();
    }

    @Override
    public ClientUserRepository clientUsers() {
        return clientUsers(null);
    }

    @Override
    public AGVRepository agvs() {
        return agvs(null);
    }

    @Override
    public AGVRepository agvs(final TransactionalContext tx) {
        return new InMemoryAGVRepository();
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

}
