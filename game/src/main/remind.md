```java
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        ObjectNode innerNode = mapper.createObjectNode();

        innerNode.put("exampleField", "hello world");

        node.put("nested", innerNode);
        try {
            WebTools.postMatchInfo("newTest", node);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
```