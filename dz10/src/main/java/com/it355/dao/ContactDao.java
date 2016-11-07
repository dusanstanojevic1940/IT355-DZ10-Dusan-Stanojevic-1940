package com.it355.dao;

import com.it355.mappers.ContactMapper;
import com.it355.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ContactDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean delete(long id, long userId) {
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM contacts WHERE id = ? AND user_id = ?", Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            preparedStatement.setLong(++i, id);
            preparedStatement.setLong(++i, userId);
            preparedStatement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public Contact findById(long id) {
        return this.jdbcTemplate.queryForObject(
                "SELECT * FROM contacts where id = ?",
                new Object[]{id},
                new ContactMapper());
    }

    public List<Contact> findAllForUser(long userId) {
        return this.jdbcTemplate.query(
                "SELECT * FROM contacts where user_id = ?",
                new Object[]{userId},
                new ContactMapper());
    }

    public Contact update(Contact contact) {
        this.jdbcTemplate.update("UPDATE contacts SET name=?, number=? WHERE id = ? AND user_id=?",
                contact.getName(),
                contact.getNumber(),
                contact.getId(),
                contact.getUserId()
        );

        return contact;
    }
    public Contact insert(Contact contact, long userId) {
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contacts (user_id, name, number) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            preparedStatement.setLong(++i, userId);
            preparedStatement.setString(++i, contact.getName());
            preparedStatement.setString(++i, contact.getNumber());

            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();

            if (keys.next()) {
                contact.setId(keys.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return contact;
    }
}
