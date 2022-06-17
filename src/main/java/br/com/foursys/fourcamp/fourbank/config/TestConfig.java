package br.com.foursys.fourcamp.fourbank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import br.com.foursys.fourcamp.fourbank.enums.CustomerTypeEnum;
import br.com.foursys.fourcamp.fourbank.model.Account;
import br.com.foursys.fourcamp.fourbank.model.Address;
import br.com.foursys.fourcamp.fourbank.model.CheckingAccount;
import br.com.foursys.fourcamp.fourbank.model.CreditCard;
import br.com.foursys.fourcamp.fourbank.model.Customer;
import br.com.foursys.fourcamp.fourbank.repository.CustomerRepository;
import br.com.foursys.fourcamp.fourbank.util.GenerateCardNumber.Label;

@Configuration
public class TestConfig implements CommandLineRunner {
	@Autowired
	private CustomerRepository customer;
	
	@Override
	public void run(String... args) throws Exception {
		
		//Criando o custommer
		Customer cliente1 = new Customer(null, "Diogo Valente", "23/06/1994", "493.314.270-00", "44-99888-1158", 1800.00, "diogo.hv@hotmail.com", "23061994", CustomerTypeEnum.STANDARD, "asdas");
		Customer cliente2 = new Customer(null, "Jose Robson", "16/01/2000", "459.474.360-94", "44-88778-0258", 17800.00, "jose.robson@hotmail.com", "11112222", CustomerTypeEnum.PREMIUM, "asdas");
		
		
		//Criando endereço
		Address a1 = new Address(null ,"Rua carochinha", "1111", "Marialva", "Jardim Itabera", "PR", "87145555", cliente1);
		Address a2 = new Address(null, "Rua Aristides Bonifácio", "444", "Marialva", "Centro", "PR", "86990000",cliente2);	
		
		//salvando cliente e endereço
		cliente1.setAddress(a1);
		cliente2.setAddress(a2);
		customer.save(cliente1);
		customer.save(cliente2);

	
		
	}


	

}