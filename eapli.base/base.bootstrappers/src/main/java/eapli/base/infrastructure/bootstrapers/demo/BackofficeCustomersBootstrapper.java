package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.CustomersBootstrapperBase;
import eapli.framework.actions.Action;

public class BackofficeCustomersBootstrapper extends CustomersBootstrapperBase implements Action{

    @Override
    public boolean execute(){
        try {
            registerCustomer("Cristiano", "Ronaldo", "PT274989220", "cr7@gmail.com", "+351 912345678", "Rua do Ronaldo___7___7777-777___Lisboa___Portugal", "male", "05-02-1985");
            registerCustomer("Ricardo", "Fazeres", "PT274989221", "riczao@gmail.com", "+351 982745621", "Rua do Ric___6___6767-767___Setubal___Portugal", "male", "05-03-1979");
            registerCustomer("Hillary", "Clinton", "ATU12345678", "hillary@gmail.com", "+351 932345678", "Hillary Clinton Street___10___1010-100___Washington City___United States", "female", "26-10-1947");
            registerCustomer("Cristina", "Ferreira", "PT274989223", "cris@gmail.com", "+351 912000678", "Rua da Cristina___2___2222-222___Lisboa___Portugal", "female", "09-09-1977");
            registerCustomer("Rafael", "Nadal", "PT274989226", "rafa@gmail.com", "+351 942345678", "Rua do Nadal___4___4444-774___Barcelona___Spain", "male", "03-06-1986");
            registerCustomer("Chin", "Linn", "HR12345678912", "chin@gmail.com", "+351 992345678", "Rua do chin___5___4545-554___Tokyo___Japan", "male", "23-12-2000");
            registerCustomer("Marco", "Horacio", "PT274989226", "marco@gmail.com", "+351 992345699", "Rua do marco___5___8548-584___Luanda___Angola", "male", "06-01-1974");
            registerCustomer("Connor", "McGregor", "PL1234567890", "connor@gmail.com", "+351 912111791", "Rua do connor___1___1111-111___Dublin___Ireland", "male", "14-07-1988");
            registerCustomer("Valtteri", "Bottas", "FRAB123456789", "bottas@gmail.com", "+351 932311793", "Rua do bottas___2___1121-121___Helsinquia___Finland", "male", "28-08-1989");
        }catch (Exception e){

        }

        return true;
    }
}
