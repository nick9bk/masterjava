package ru.javaops.masterjava.xml;

import com.google.common.io.Resources;
import ru.javaops.masterjava.xml.schema.ObjectFactory;
import ru.javaops.masterjava.xml.schema.Payload;
import ru.javaops.masterjava.xml.schema.Project;
import ru.javaops.masterjava.xml.schema.User;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainXml {
    private final JaxbParser JAXB_PARSER;
    private final String fileXML;

    public MainXml(String fileXSD, String fileXML) {
        JAXB_PARSER = new JaxbParser(ObjectFactory.class);
        JAXB_PARSER.setSchema(Schemas.ofClasspath(fileXSD));
        this.fileXML = fileXML;
    }

    public List<User> getSort(String projectName) throws IOException, JAXBException {
        Payload payload = JAXB_PARSER.unmarshal(Resources.getResource(fileXML).openStream());
        List<User> users = payload.getUsers().getUser();
        Project project = payload.getProjects().getProject().stream().filter(u -> u.getName().equals(projectName)).findAny().get();
        return users.stream().filter(user -> {
            for (Project.Group g : project.getGroup()) {
                if (user.getGroupRefs().contains(g)) {
                    return true;
                }
            }
            return false;
        }).sorted(Comparator.comparing(User::getValue)).collect(Collectors.toList());
    }
}
