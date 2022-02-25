package com.answerdigital.colourstest.service;

import com.answerdigital.colourstest.dto.PersonUpdateDTO;
import com.answerdigital.colourstest.model.Person;
import com.answerdigital.colourstest.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRespository;

    public List<Person> findAllPerson() {
        return peopleRespository.findAll();
    }

    public ResponseEntity<Person> findPerson(long id) {
        Optional<Person> personToFind = peopleRespository.findById(id);
        if (!personToFind.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(personToFind.get(), HttpStatus.OK);
    }

    public ResponseEntity<Person> updatePerson(Long id, PersonUpdateDTO personDTO) {
        Optional<Person> findPerson = peopleRespository.findById(id);
        if (findPerson.isPresent()) {
            Person person = findPerson.get();
            person.setAuthorised(personDTO.isAuthorised());
            person.setEnabled(personDTO.isEnabled());
            person.setColours(personDTO.getColours());
            peopleRespository.save(person);
            return new ResponseEntity<>(findPerson.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Person> addNewPerson(Person person) {
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        peopleRespository.save(person);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
