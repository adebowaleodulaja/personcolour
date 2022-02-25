package com.answerdigital.colourstest.controller;

import com.answerdigital.colourstest.model.Colour;
import com.answerdigital.colourstest.repository.ColoursRepository;
import java.util.List;

import com.answerdigital.colourstest.service.ColourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Colours")
public class ColoursController {

    @Autowired
    private ColoursRepository coloursRepository;

    @Autowired
    ColourService colourService;

    @GetMapping
    public ResponseEntity<List<Colour>> getColours() {
        return new ResponseEntity(coloursRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colour> getPerson(@PathVariable("id") long id) {
        return colourService.findColour(id);
    }

    @PostMapping("/create-colour")
    public ResponseEntity<Colour> createNewColour(@RequestBody Colour colour) {
        return colourService.addNewColour(colour);
    }
}
