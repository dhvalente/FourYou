package br.com.foursys.fourcamp.fourbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foursys.fourcamp.fourbank.dto.MessageResponseDTO;
import br.com.foursys.fourcamp.fourbank.exceptions.PixNotFoundException;
import br.com.foursys.fourcamp.fourbank.model.Pix;
import br.com.foursys.fourcamp.fourbank.repository.PixRepository;

@Service
public class PixService {

    private PixRepository pixRepository;

    @Autowired
    public PixService(PixRepository pixRepository) {
        this.pixRepository = pixRepository;
    }

    public MessageResponseDTO createPix(Pix pix) {
        Pix savedPix = getPix(pix);
        return createMessageResponse(savedPix.getId(), "Created ");
    }

    public List<Pix> listAll() {
        return pixRepository.findAll();
    }

    public MessageResponseDTO updateById(String id, Pix pix) throws PixNotFoundException {
        verifyIfExists(id);
        Pix validPix = pixIsValid(pix);
        Pix updatedPix = getPix(validPix);
        return createMessageResponse(updatedPix.getId(), "Updated ");
    }

    public void delete(String id) throws PixNotFoundException {
        verifyIfExists(id);
        pixRepository.deleteById(id);
    }

    private Pix getPix(Pix pix) {
        Pix validPix = pixIsValid(pix);
        return pixRepository.save(pix);
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO.builder()
                .message(s + "Pix com a id " + id)
                .build();
    }

    private Pix verifyIfExists(String id) throws PixNotFoundException {
        return pixRepository.findById(id)
                .orElseThrow(() -> new PixNotFoundException(id));
    }

    private Pix pixIsValid(Pix pix) {
        //validações
        return pix;
    }

    public Pix findById(String id) throws PixNotFoundException {
        return verifyIfExists(id);
    }

}
