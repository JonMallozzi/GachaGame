package com.example.demo.api;

import com.example.demo.model.person;
import com.example.demo.service.personService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@RequestMapping("api/v1/person")
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class personController {

    private final personService personService;

    @Autowired
    public personController(personService personService){
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }

}
