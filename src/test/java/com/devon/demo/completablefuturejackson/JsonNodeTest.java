package com.devon.demo.completablefuturejackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JsonNodeTest {

    @Test
    public void jsonTest1() {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();

        JsonNode metaNode = mapper.createObjectNode();
        ((ObjectNode) metaNode).put("serviceVersion", "2");
        ((ObjectNode) metaNode).put("schemaVersion", "3");
        ((ObjectNode) metaNode).put("clientId", "34");

        JsonNode element = mapper.createObjectNode();
        ((ObjectNode) element).put("name", "retailAmount");
        Set<String> setOfStuff = new HashSet<String>();
        setOfStuff.add("sdf");
        setOfStuff.add("sdDf");

        setOfStuff.add("sdSf");

        ArrayNode valueArrayNode = mapper.createArrayNode();
        setOfStuff.forEach(value -> {
            valueArrayNode.add(value);
        });


        ((ObjectNode) element).set("value", valueArrayNode);


        JsonNode invalid = mapper.createObjectNode();
        ((ObjectNode) invalid).put("description", "whatever");
        ((ObjectNode) invalid).put("sumZero", true);
        ((ObjectNode) invalid).set("element", element);

        JsonNode invalid2 = mapper.createObjectNode();
        ((ObjectNode) invalid2).set("invalid", invalid);


        ((ObjectNode) rootNode).set("meta", metaNode);
        ((ObjectNode) rootNode).set("data", invalid2);


        String jsonString = null;
        try {
            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
            System.out.println(jsonString);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        // just for pull request testing
    }

    @Test
    public void test3() {
        // just for pull request testing
    }
}
