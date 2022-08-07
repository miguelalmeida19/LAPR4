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
package eapli.base.infrastructure.persistence;

import eapli.base.agvmanagement.domain.repositories.AGVRepository;
import eapli.base.agvmanagement.domain.repositories.AgvModelRepository;
import eapli.base.clientusermanagement.repositories.ClientUserRepository;
import eapli.base.clientusermanagement.repositories.SignupRequestRepository;
import eapli.base.customermanagement.domain.repositories.CustomerRepository;
import eapli.base.ordermanagement.domain.domain.repositories.OrderItemRepository;
import eapli.base.ordermanagement.domain.domain.repositories.OrderRepository;
import eapli.base.productmanagement.domain.repositories.ProductCategoryRepository;
import eapli.base.productmanagement.domain.repositories.ProductRepository;
import eapli.base.surveymanagement.domain.model.Survey;
import eapli.base.surveymanagement.domain.repositories.SurveyAnswerRepository;
import eapli.base.surveymanagement.domain.repositories.SurveyRepository;
import eapli.base.warehousemanagement.domain.repositories.*;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the repositories
     *
     * @return
     */
    TransactionalContext newTransactionalContext();

    /**
     *
     * @param autoTx
     *            the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    CustomerRepository customers();

    CustomerRepository customers(TransactionalContext tx);

    OrderRepository orders();

    OrderRepository orders(TransactionalContext tx);

    OrderItemRepository orderItems();

    OrderItemRepository orderItems(TransactionalContext tx);
    AgvModelRepository agvModels();
    AgvModelRepository agvModels(TransactionalContext tx);


    ProductCategoryRepository productCategories();

    ProductCategoryRepository productCategories(TransactionalContext tx);

    ProductRepository products();

    ProductRepository products(TransactionalContext tx);

    WarehouseRepository warehouses();

    WarehouseRepository warehouses(TransactionalContext tx);

    SquareRepository squares();

    SquareRepository squares(TransactionalContext tx);

    AisleRepository aisles();

    AisleRepository aisles(TransactionalContext tx);

    RowRepository rows();

    RowRepository rows(TransactionalContext tx);

    AGVRepository agvs();

    AGVRepository agvs(TransactionalContext tx);

    AGVDockRepository agvDocks();

    AGVDockRepository agvDocks(TransactionalContext tx);

    SurveyRepository surveys();

    SurveyRepository surveys(TransactionalContext tx);

    SurveyAnswerRepository surveyAnswers();

    SurveyAnswerRepository surveyAnswers(TransactionalContext tx);

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    ClientUserRepository clientUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    ClientUserRepository clientUsers();

    /**
     *
     * @param autoTx
     *            the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();

}
