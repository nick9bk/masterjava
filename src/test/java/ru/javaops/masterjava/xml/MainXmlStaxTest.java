package ru.javaops.masterjava.xml;

import org.junit.Test;

import java.util.List;

public class MainXmlStaxTest {

    @Test
    public void getSort() {
        List<String> users = new MainXmlStax("payload.xml").getSort("topjava");
        users.stream().forEach(user -> System.out.println(user));
    }
}