package br.com.foursys.fourcamp.fourbank.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.foursys.fourcamp.fourbank.model.Address;
import br.com.foursys.fourcamp.fourbank.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	AddressRepository addressRepository;
	public String findAddressByCep(String cep) {
        String json;
        try {
            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder jsonSb = new StringBuilder();
            br.lines().forEach(l -> jsonSb.append(l.trim()));
            json = jsonSb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return json;
    }
	
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

	public Address findById(Long id) {
		Optional<Address> obj = addressRepository.findById(id);
		return obj.get();
	}

	public Address insert(Address obj) {
		return addressRepository.save(obj);
	}

	public void delete(Long id) {	
		addressRepository.deleteById(id);
		
	}

	public Address update(Long id, Address obj) {
		Address entity = addressRepository.getReferenceById(id);
		updateData(entity, obj);
		return addressRepository.save(entity);
	}

	private void updateData(Address entity, Address obj) {
		entity.setStreet(obj.getStreet());
		entity.setNumber(obj.getNumber());
		entity.setCity(obj.getCity());
		entity.setDistrict(obj.getDistrict());
		entity.setState(obj.getState());
		entity.setZipcode(obj.getZipcode());
	}
	
	
}
