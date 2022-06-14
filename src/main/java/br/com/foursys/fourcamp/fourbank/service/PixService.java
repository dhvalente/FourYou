package com.fourcamp.fourcamp22.java.group4.service;

import com.fourcamp.fourcamp22.java.group4.dto.response.MessageResponseDTO;
import com.fourcamp.fourcamp22.java.group4.exceptions.PixNotFoundException;
import com.fourcamp.fourcamp22.java.group4.model.Pix;
import com.fourcamp.fourcamp22.java.group4.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public MessageResponseDTO updateById(Long id, Pix pix) throws PixNotFoundException {
        verifyIfExists(id);
        Pix validPix = pixIsValid(pix);
        Pix updatedPix = getPix(validPix);
        return createMessageResponse(updatedPix.getId(), "Updated ");
    }

    public void delete(Long id) throws PixNotFoundException {
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

    private Pix verifyIfExists(Long id) throws PixNotFoundException {
        return pixRepository.findById(id)
                .orElseThrow(() -> new PixNotFoundException(id));
    }

    private Pix pixIsValid(Pix pix) {
        //validações
        return pix;
    }

    public Pix findById(Long id) throws PixNotFoundException {
        return verifyIfExists(id);
    }

}
