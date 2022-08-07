package eapli.base.clientusermanagement.utils;

import eapli.base.clientusermanagement.application.AcceptRefuseSignupFactory;
import eapli.base.clientusermanagement.application.AcceptRefuseSignupRequestController;
import eapli.base.clientusermanagement.domain.SignupRequest;
import eapli.base.myclientuser.application.SignupController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

import static org.hibernate.bytecode.BytecodeLogger.LOGGER;


public class SignupUtils {

    private final AcceptRefuseSignupRequestController acceptController = AcceptRefuseSignupFactory
            .build();

    private final SignupController signupController = new SignupController();

    public SignupRequest signupAndApprove(final String username, final String password,
                                          final String firstName, final String lastName, final String email,
                                          final String mecanographicNumber) {
        SignupRequest request = null;
        try {
            request = signupController.signup(username, password, firstName, lastName, email,
                    mecanographicNumber);
            acceptController.acceptSignupRequest(request);
        } catch (final ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.trace("Assuming existing record", e);
        }
        return request;
    }

}
