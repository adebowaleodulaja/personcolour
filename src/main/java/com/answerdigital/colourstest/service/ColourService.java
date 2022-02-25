package com.answerdigital.colourstest.service;

import com.answerdigital.colourstest.model.Colour;
import com.answerdigital.colourstest.repository.ColoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColourService {

    @Autowired
    private ColoursRepository coloursRepository;

    public ResponseEntity<Colour> findColour(long id) {
        Optional<Colour> colourToFind = coloursRepository.findById(id);
        if (!colourToFind.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(colourToFind.get(), HttpStatus.OK);
    }

    public ResponseEntity<Colour> addNewColour(Colour colour) {
        if (colour != null) {
            coloursRepository.save(colour);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
