package com.alextrejo.exercise2.service;

import com.alextrejo.exercise2.dto.PersonDTO;
import com.alextrejo.exercise2.model.Person;
import com.alextrejo.exercise2.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Person getPersonById(String id){
        return personRepository.findById(id).orElseThrow( () -> new RuntimeException("Person with id: "+ id + " not found."));
    }

    public Person createPerson(PersonDTO personDTO){
        Person person = new Person();
        person.setFirstName(personDTO.firstName());
        person.setLastName(personDTO.lastName());
        person.setAge(personDTO.age());
        return personRepository.save(person);
    }


}
