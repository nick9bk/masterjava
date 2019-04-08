package ru.javaops.masterjava.xml;

import org.junit.Test;
import ru.javaops.masterjava.xml.schema.User;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public class MainXmlTest {

    @Test
    public void getSort() throws IOException, JAXBException {
        List<User> users = new MainXml("payload.xsd", "payload.xml").getSort("topjava");
        users.stream().forEach(user -> System.out.println(user.getValue()));
    }
}