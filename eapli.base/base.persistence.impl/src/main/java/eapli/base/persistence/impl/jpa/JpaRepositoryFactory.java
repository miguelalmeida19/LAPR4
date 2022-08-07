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
package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.agvmanagement.domain.repositories.AGVRepository;
import eapli.base.agvmanagement.domain.repositories.AgvModelRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.customermanagement.domain.repositories.CustomerRepository;
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
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaClientUserRepository clientUsers(final TransactionalContext autoTx) {
        return new JpaClientUserRepository(autoTx);
    }

    @Override
    public JpaClientUserRepository clientUsers() {
        return new JpaClientUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CustomerRepository customers() {
        return new JpaAutoTxCustomerRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public CustomerRepository customers(final TransactionalContext tx) {
        return new JpaAutoTxCustomerRepository(tx);
    }

    @Override
    public OrderRepository orders() {
        return new JpaAutoTxOrderRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public OrderRepository orders(final TransactionalContext tx) {
        return new JpaAutoTxOrderRepository(tx);
    }

    @Override
    public OrderItemRepository orderItems() {
        return new JpaAutoTxOrderItemRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public OrderItemRepository orderItems(final TransactionalContext tx) {
        return new JpaAutoTxOrderItemRepository(tx);
    }

    @Override
    public AgvModelRepository agvModels() {
        return new JpaAutoTxAgvModelRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());    }

    @Override
    public AgvModelRepository agvModels(TransactionalContext tx) {
        return new JpaAutoTxAgvModelRepository(tx);
    }

    @Override
    public ProductCategoryRepository productCategories() {
        return new JpaAutoTxProductCategoryRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public ProductCategoryRepository productCategories(final TransactionalContext tx) {
        return new JpaAutoTxProductCategoryRepository(tx);
    }

    @Override
    public ProductRepository products() {
        return new JpaAutoTxProductRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public ProductRepository products(final TransactionalContext tx) {
        return new JpaAutoTxProductRepository(tx);
    }

    @Override
    public WarehouseRepository warehouses() {
        return new JpaAutoTxWarehouseRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public WarehouseRepository warehouses(final TransactionalContext tx) {
        return new JpaAutoTxWarehouseRepository(tx);
    }

    @Override
    public SquareRepository squares() {
        return new JpaAutoTxSquaresRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public SquareRepository squares(TransactionalContext tx) {
        return new JpaAutoTxSquaresRepository(tx);
    }

    @Override
    public AisleRepository aisles() {
        return new JpaAutoTxAislesRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public AisleRepository aisles(final TransactionalContext tx) {
        return new JpaAutoTxAislesRepository(tx);
    }

    @Override
    public RowRepository rows() {
        return new JpaAutoTxRowsRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public RowRepository rows(final TransactionalContext tx) {
        return new JpaAutoTxRowsRepository(tx);
    }

    @Override
    public AGVDockRepository agvDocks() {
        return new JpaAutoTxAGVDockRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public AGVDockRepository agvDocks(final TransactionalContext tx) {
        return new JpaAutoTxAGVDockRepository(tx);
    }

    @Override
    public SurveyRepository surveys() {
        return new JpaAutoTxSurveyRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public SurveyRepository surveys(TransactionalContext tx) {
        return new JpaAutoTxSurveyRepository(tx);
    }

    @Override
    public SurveyAnswerRepository surveyAnswers() {
        return new JpaAutoTxSurveyAnswerRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public SurveyAnswerRepository surveyAnswers(TransactionalContext tx) {
        return new JpaAutoTxSurveyAnswerRepository(tx);
    }

    @Override
    public AGVRepository agvs() {
        return new JpaAutoTXAGVRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public AGVRepository agvs(final TransactionalContext tx) {
        return new JpaAutoTXAGVRepository(tx);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

}
