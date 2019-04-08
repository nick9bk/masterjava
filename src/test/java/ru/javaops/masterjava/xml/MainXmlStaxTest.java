package ru.javaops.masterjava.xml;

import org.junit.Test;
import ru.javaops.masterjava.xml.schema.User;

import java.util.List;

public class MainXmlStaxTest {

    @Test
    public void getSort() {
        List<User> users = new MainXmlStax("payload.xml").getSort("topjava");
        //users.stream().forEach(user -> System.out.println(user.getValue()));
    }
}