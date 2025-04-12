package com.MessageQueue.Framework.Utils;

import com.MessageQueue.Framework.Model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {
    @Override
    public void serialize(User user, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException{
        generator.writeStartObject();
        generator.writeStringField("id", user.getId().toString());
        generator.writeStringField("firstName", user.getFirstName());
        generator.writeStringField("lastName", user.getLastName());
        generator.writeStringField("userName", user.getUserName());
        generator.writeStringField("password", user.getPassword());
        generator.writeStringField("dateOfBirth", user.getDateOfBirth().toString());
        generator.writeEndObject();
    }
}
