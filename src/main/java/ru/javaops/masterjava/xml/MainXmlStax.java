package ru.javaops.masterjava.xml;

import com.google.common.io.Resources;
import ru.javaops.masterjava.xml.schema.Project;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MainXmlStax {
    private String fileXml;

    public MainXmlStax(String fileXML) {
        this.fileXml = fileXML;
    }

    public List<String> getSort(String projectName) {
        try (StaxStreamProcessor processor =
                     new StaxStreamProcessor(Resources.getResource("payload.xml").openStream())) {
            XMLStreamReader reader = processor.getReader();
            List<String> groups = new LinkedList<>();
            while (processor.doUntil(XMLEvent.START_ELEMENT, "Project")) {
                if (projectName.equals(reader.getAttributeValue(0))) {
                    while (processor.doUntil(XMLEvent.START_ELEMENT, "Group", XMLEvent.END_ELEMENT, "Project")) {
                        new Project.Group();
                        groups.add(reader.getAttributeValue(0));
                    }
                    break;
                }
            }
            List<String> users = new LinkedList<>();
            while (processor.doUntil(XMLEvent.START_ELEMENT, "User")) {
                final List<String> gr = Arrays.asList(reader.getAttributeValue(3).split(" "));
                if (groups.stream().anyMatch(gr::contains)) {
                    users.add(reader.getElementText());
                }
            }
            return users.stream().sorted().collect(Collectors.toList());
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
