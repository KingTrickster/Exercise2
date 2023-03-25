package com.alextrejo.exercise2.api.v1;

import com.alextrejo.exercise2.dto.PersonDTO;
import com.alextrejo.exercise2.model.Person;
import com.alextrejo.exercise2.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController @RequestMapping("api/v1/persons") @AllArgsConstructor
public class PersonController {
    private PersonService personService;

    @GetMapping
    public ResponseEntity<Object> getAllPersons(){
        if(personService.getAllPersons().isEmpty()){
            return new ResponseEntity<>("No persons found.", HttpStatus.OK);
        }
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> getPersonById(@PathVariable String personId){
        return new ResponseEntity<>(personService.getPersonById(personId),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> savePerson(@RequestBody PersonDTO personDTO){
        if (personDTO.firstName().equals(null) || personDTO.firstName().equals("")) {
            return new ResponseEntity<>(
                    "First name of the person cannot be blank or null",
                    HttpStatus.BAD_REQUEST);
        }
        if (personDTO.lastName().equals(null) || personDTO.lastName().equals("")) {
            return new ResponseEntity<>(
                    "Last name of the person cannot be blank or null",
                    HttpStatus.BAD_REQUEST);
        }
        if (personDTO.age() < 0 ) {
            return new ResponseEntity<>(
                    "Age of a person cannot be negative.",
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(personService.createPerson(personDTO), HttpStatus.CREATED);
    }
}
