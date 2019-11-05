package com.example.demo.service;

import com.example.demo.dao.personDAO;
import com.example.demo.model.person;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class personService {

    private final personDAO personDao;

    @Autowired
    public personService(@Qualifier("postgres") personDAO personDao){
        this.personDao = personDao;
    }

    public int addPerson(person person){
        return personDao.insertPerson(person);
    }

    public List<person> getAllPeople(){
        return personDao.selectAllPeople();
    }

    public Optional<person> getPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id, person newPerson){
        return personDao.updatePersonById(id, newPerson);
    }

}
