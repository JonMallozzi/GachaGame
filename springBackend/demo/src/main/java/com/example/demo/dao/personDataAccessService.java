package com.example.demo.dao;

import com.example.demo.model.person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class personDataAccessService implements personDAO {


    private final JdbcTemplate jdbcTemplate;

    public personDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, person person) {
        final String sql = "INSERT INTO person(id,name,birthday) " +
                "VALUES (uuid_generate_v4(), ?, ?)";
        jdbcTemplate.update(sql,person.getName(),person.getBirthday());
        return 2;
    }

    @Override
    public List<person> selectAllPeople() {
        final String sql = "SELECT * FROM person";
        List<person> people = jdbcTemplate.query(sql, (resultSet, i) -> {
           UUID id = UUID.fromString(resultSet.getString("id"));
           String name = resultSet.getString("name");
           Date birthday = resultSet.getDate("birthday");
            return new person(id, name, birthday);
        });
           return people;
    }

    @Override
    public Optional<person> selectPersonById(UUID id) {


        final String sql = "SELECT * FROM person WHERE id = ?";

        person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    Date birthday = resultSet.getDate("birthday");
                    return new person(personId,name,birthday);
                });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {

        final String sql = "DELETE FROM person WHERE id = ?";

        jdbcTemplate.update(sql, new Object[]{id});

        return 1;
    }

    @Override
    public int updatePersonById(UUID id, person person) {

        final String sql = "UPDATE person SET name = ?, birthday = ? WHERE id = ?";

        jdbcTemplate.update(sql,person.getName(),person.getBirthday(),id);

        return 1;
    }
}
