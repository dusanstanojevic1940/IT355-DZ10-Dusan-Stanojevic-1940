package com.it355.mappers;

import com.it355.models.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements RowMapper<Contact> {

    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();

        contact.setId(rs.getLong("id"));
        contact.setName(rs.getString("name"));
        contact.setNumber(rs.getString("number"));
        contact.setUserId(rs.getLong("user_id"));

        return contact;
    }
}
