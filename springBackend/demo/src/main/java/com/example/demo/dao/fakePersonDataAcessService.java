package com.example.demo.dao;

import com.example.demo.model.person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDAO")
public class fakePersonDataAcessService implements personDAO {

    private static List<person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, person person) {
        DB.add((new person(id,person.getName(),person.getBirthday())));
        return 1;
    }

    @Override
    public List<person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<person> personMaybe = selectPersonById(id);

        if(personMaybe.isEmpty())
            return 0;

        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, person person) {
        return selectPersonById(id).map(p -> {
            int indexOfPersonToUpdate = DB.indexOf(p);
            if ( indexOfPersonToUpdate >= 0) {
                DB.set(indexOfPersonToUpdate, new person(id, person.getName(), person.getBirthday()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

}
