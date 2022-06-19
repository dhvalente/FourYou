package br.com.foursys.fourcamp.fourbank.service;

import br.com.foursys.fourcamp.fourbank.model.CheckingAccount;
import br.com.foursys.fourcamp.fourbank.repository.CheckingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class CheckingAccountService {

	@Autowired
	private CheckingAccountRepository checkingAccountRepository;

	@Autowired
	public CheckingAccountService(CheckingAccountRepository checkingAccountRepository) {
		this.checkingAccountRepository = checkingAccountRepository;
	}

	public Optional<CheckingAccount> findById(Integer id) {
		return checkingAccountRepository.findById(id);
	}
	public List<CheckingAccount> findAll() {

		return checkingAccountRepository.findAll();
	}

	public CheckingAccount save(CheckingAccount obj) {
		return checkingAccountRepository.save(obj);
	}

	public CheckingAccount update(Integer id, CheckingAccount obj) {
		CheckingAccount entity = checkingAccountRepository.getOne(id);
		upData(entity, obj);
		return checkingAccountRepository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		checkingAccountRepository.deleteById(id);

	}

	public void maintenance(CheckingAccount check) {
		if (check.getBalance() < 3000.00) {
			check.setMaintenanceFee(10.00);
		} else if (check.getBalance() >= 3000.00 && check.getBalance() <= 6000.00) {
			check.setMaintenanceFee(20.00);
		} else if (check.getBalance() > 6000.00) {
			check.setMaintenanceFee(30.00);
		}
		
	}

	public void descontMaintenance(CheckingAccount check) {
    	Calendar calendar = Calendar.getInstance();
    	if(Calendar.DAY_OF_MONTH == calendar.get(Calendar.DAY_OF_MONTH)) {
    		check.setBalance(check.getBalance() - check.getMaintenanceFee());
    	}
    }

	private void upData(CheckingAccount entity, CheckingAccount obj) {
		entity.setBank(obj.getBank());
		entity.setNumAgency(obj.getNumAgency());
		entity.setNumber(obj.getNumber());

	}
}