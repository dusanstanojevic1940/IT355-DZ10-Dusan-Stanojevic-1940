package com.it355.dao;

import com.it355.mappers.ContactMapper;
import com.it355.mappers.UserMapper;
import com.it355.models.Contact;
import com.it355.models.User;
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
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void init(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public boolean delete(long id) {
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?", Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            preparedStatement.setLong(++i, id);
            preparedStatement.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<User> findAll() {
        return this.jdbcTemplate.query(
                "SELECT * FROM users",
                new Object[] {},
                new UserMapper());
    }
    public List<User> findById(long id) {
        return this.jdbcTemplate.query(
                "SELECT * FROM users WHERE id = ?",
                new Object[] {id},
                new UserMapper());
    }

    public User update(User user) {
        this.jdbcTemplate.update("UPDATE users SET username=?, password=? WHERE id = ?",
                user.getUsername(),
                user.getPassword(),
                user.getId()
        );

        return user;
    }
    public User insert(User user) {
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            preparedStatement.setString(++i, user.getUsername());
            preparedStatement.setString(++i, user.getPassword());

            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();

            if (keys.next()) {
                user.setId(keys.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
