//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package eapli.base.customermanagement.domain.model;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.model.DomainFactory;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Colocar acceptance criteria aqui!
 */

public class CustomerBuilder implements DomainFactory<Customer> {
    private static final Logger LOGGER = LogManager.getLogger(CustomerBuilder.class);
    private CustomerId customerId;
    private Name name;
    private Vat vat;
    private Email email;
    private PhoneNumber phoneNumber;
    private Addresses addresses;
    private Gender gender;
    private Birthdate birthdate;
    private SystemUser user;

    private final String SEPARATOR = "___";

    public CustomerBuilder() {
    }

    public CustomerBuilder with(final String firstName, final String lastName, final String vat, final String email, final String phoneNumber, final String addresses, final String gender, final String birthDate, final String username) {
        this.withName(firstName, lastName);
        this.withVat(vat);
        this.withEmail(email);
        this.withPhoneNumber(phoneNumber);
        this.withAddresses(addresses);
        this.withGender(gender);
        this.withBirthdate(birthDate);
        List<SystemUser> list = (List<SystemUser>) PersistenceContext.repositories().users().findAll();
        this.user = list.get(list.size()-1);
        return this;
    }

    public CustomerBuilder withName(final String firstName, final String lastName) {

        int MAX_NAME_LENGTH = 40;
        int MIN_NAME_LENGTH = 4;
        if (firstName.length() <= MAX_NAME_LENGTH && firstName.length() >= MIN_NAME_LENGTH && lastName.length() <= MAX_NAME_LENGTH && lastName.length() >= MIN_NAME_LENGTH) {
            this.name = new Name(firstName, lastName);
            return this;
        } else {
            throw new IllegalArgumentException("Name cannot be that long/short, please consider including only your first and then last name.");
        }

    }

    public CustomerBuilder withVat(final String vat) {
        if (vat!=null) {
            this.vat = Vat.valueOf(vat);
            return this;
        } else {
            throw new IllegalArgumentException("Vat format is invalid!");
        }
    }

    public CustomerBuilder withEmail(final String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()){
            this.email = Email.valueOf(email);
            return this;
        }else {
            throw new IllegalArgumentException("Email format is not valid ☹️");
        }
    }

    public CustomerBuilder withPhoneNumber(final String phoneNumber) {
        try{
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber number = new Phonenumber.PhoneNumber();
            String countryCode = phoneNumber.split(" ")[0].replace("+","");
            String phone = phoneNumber.split(" ")[1];
            number.setCountryCode(Integer.parseInt(countryCode)).setNationalNumber(Long.parseLong(phone));
            if (phoneNumberUtil.isPossibleNumber(number)){
                this.phoneNumber = PhoneNumber.valueOf(phoneNumber);
                return this;
            }else {
                throw new IllegalArgumentException("Phone number format is invalid!");
            }
        }catch (Exception e){
            throw new IllegalArgumentException("Phone number format is invalid!");
        }
    }

    public CustomerBuilder withAddresses(final String addresses) {

        try{
            String[] addressInfo = addresses.split(SEPARATOR);
            String streetName = addressInfo[0];
            String doorNumber = addressInfo[1];
            String postalCode = addressInfo[2];
            String city = addressInfo[3];
            String country = addressInfo[4];

            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

            if (!streetName.isEmpty() && pattern.matcher(doorNumber).matches() && !postalCode.isEmpty() && !city.isEmpty() && !country.isEmpty()){
                this.addresses = Addresses.valueOf(addresses);
                return this;
            }else {
                throw new IllegalArgumentException("Addresses format is not valid!");
            }

        }catch (Exception e){
            throw new IllegalArgumentException("Addresses format is not valid!");
        }
    }

    public CustomerBuilder withGender(final String gender) {
        this.gender = Gender.valueOf(gender);
        return this;
    }

    public CustomerBuilder withBirthdate(final String birthdate) {

        try{
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            final LocalDateTime dt = LocalDateTime.parse(birthdate + " 00:00", formatter);

            long age = LocalDate.from(dt).until(LocalDate.now(), ChronoUnit.YEARS);
            if (age>=18){
                this.birthdate = Birthdate.valueOf(birthdate);
                return this;
            }else {
                throw new IllegalArgumentException("Sorry, you are too young! Must be 18 years at least!");
            }

        }catch (Exception e){
            throw new IllegalArgumentException("Your birthdate is not valid");
        }
    }


    public Customer build() {
        Customer customer = new Customer(this.email, this.gender, this.name, this.vat, this.phoneNumber, this.addresses, this.birthdate, this.user);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Creating new customer [{}]", this.customerId);
        }

        return customer;
    }
}
