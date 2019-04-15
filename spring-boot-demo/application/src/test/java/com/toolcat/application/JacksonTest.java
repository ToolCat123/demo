package com.toolcat.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toolcat.application.entity.User;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Jackson
 * <p>
 * 配置mapper
 * //在反序列化时忽略在 json 中存在但 Java 对象不存在的属性
 * mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
 * //在序列化时日期格式默认为 yyyy-MM-dd'T'HH:mm:ss.SSSZ
 * mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
 * //在序列化时忽略值为 null 的属性
 * mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
 * //忽略值为默认值的属性
 * mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
 * <p>
 * 常用注解
 *
 * @JsonProperty 用于属性，把属性的名称序列化时转换为另外一个名称。
 * 示例：
 * @JsonProperty("birth_ d ate")
 * private Date birthDate;
 * @JsonFormat 用于属性或者方法，把属性的格式序列化时转换成指定的格式。
 * 示例：
 * @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
 * public Date getBirthDate()
 * @JsonPropertyOrder 用于类， 指定属性在序列化时 json 中的顺序 ，
 * 示例：
 * @JsonPropertyOrder({ "birth_Date", "name" })
 * public class Person
 * @JsonCreator 用于构造方法，和 @JsonProperty 配合使用，适用有参数的构造方法。
 * 示例：
 * @JsonCreator public Person(@JsonProperty("name")String name) {…}
 * @JsonAnySetter 用于属性或者方法，设置未反序列化的属性名和值作为键值存储到 map 中。
 * 示例：
 * @JsonAnySetter public void set(String key, Object value) {
 * map.put(key, value);
 * }
 * @JsonAnyGetter 用于方法 ，获取所有未序列化的属性。
 * 示例：
 * @JsonAnyGetter public Map<String, Object> any() { return map; }
 */

class JacksonTest {

    // Object -> Json
    @Test
    void test0() {

        ObjectMapper mapper = new ObjectMapper();

        User user = new User();
        user.setId(1L);
        user.setUsername("番茄炒鸡蛋");
        user.setAge(13L);
        user.setGender("男");
        user.setAddress("非洲");
        user.setPhone("13254687954");
        user.setEmail("13254687954@qq.com");
        user.setPassword("123456");
        user.setBirthday(new DateTime(2020, 6, 1, 0, 0, 0).toDate());

        try {
            System.out.println(mapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            System.out.println("user异常");
        }

    }

    // Json -> Object
    @Test
    void test1() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"小峰\",\n" +
                "  \"age\": 13,\n" +
                "  \"gender\": null,\n" +
                "  \"address\": \"非洲\",\n" +
                "  \"phone\": \"13254687954\",\n" +
                "  \"email\": \"13254687954@qq.com\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"birthday\": 1590940800000\n" +
                "}";
        try {
            System.out.println(mapper.readValue(json, User.class).toString());
        } catch (JsonProcessingException e) {
            System.out.println("string异常");
        }
    }

    // Reader -> Object
    @Test
    void test2() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"小峰\",\n" +
                "  \"age\": 13,\n" +
                "  \"gender\": null,\n" +
                "  \"address\": \"非洲\",\n" +
                "  \"phone\": \"13254687954\",\n" +
                "  \"email\": \"13254687954@qq.com\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"birthday\": 1590940800000\n" +
                "}";
        Reader reader = new StringReader(json);
        try {
            System.out.println(mapper.readValue(reader, User.class).toString());
        } catch (IOException e) {
            System.out.println("reader异常");
            ;
        }
    }

    // File -> Object
    @Test
    void test3() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/test/java/com/toolcat/application/user.json");
        try {
            System.out.println(mapper.readValue(file, User.class).toString());
        } catch (IOException e) {
            System.out.println("file异常");
        }
    }

    // URL -> Object
    @Test
    void test4() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            URL url = new URL("file:src/test/java/com/toolcat/application/user.json");
            System.out.println(mapper.readValue(url, User.class).toString());
        } catch (MalformedURLException e) {
            System.out.println("user.json不存在");
        } catch (IOException e) {
            System.out.println("url异常");
        }
    }

    // InputStream -> Object
    @Test
    void test5() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = new FileInputStream("src/test/java/com/toolcat/application/user.json");
            System.out.println(mapper.readValue(inputStream, User.class).toString());
        } catch (FileNotFoundException e) {
            System.out.println("user.json不存在");
        } catch (IOException ignored) {
            System.out.println("InputStream异常");
        }
    }

    // byte[] -> Object
    @Test
    void test6() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"小峰\",\n" +
                "  \"age\": 13,\n" +
                "  \"gender\": null,\n" +
                "  \"address\": \"非洲\",\n" +
                "  \"phone\": \"13254687954\",\n" +
                "  \"email\": \"13254687954@qq.com\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"birthday\": 1590940800000\n" +
                "}";
        try {
            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
            System.out.println(mapper.readValue(bytes, User.class).toString());
        } catch (UnsupportedEncodingException e) {
            System.out.println("byte[]这是失败");
        } catch (IOException e) {
            System.out.println("byte[]异常");
        }
    }

    // Json -> Object[]
    @Test
    void test7() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonArray = "[\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"鸡蛋炒番茄\",\n" +
                "    \"age\": 24,\n" +
                "    \"gender\": \"女\",\n" +
                "    \"address\": \"南极洲\",\n" +
                "    \"phone\": \"13287654329\",\n" +
                "    \"email\": \"13287654329@qq.com\",\n" +
                "    \"password\": \"123456\",\n" +
                "    \"birthday\": 1588262400000\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"番茄炒鸡蛋\",\n" +
                "    \"age\": 13,\n" +
                "    \"gender\": \"男\",\n" +
                "    \"address\": \"非洲\",\n" +
                "    \"phone\": \"13254687954\",\n" +
                "    \"email\": \"13254687954@qq.com\",\n" +
                "    \"password\": \"123456\",\n" +
                "    \"birthday\": 1590940800000\n" +
                "  }\n" +
                "]";
        try {
            System.out.println(Arrays.toString(mapper.readValue(jsonArray, User[].class)));
        } catch (JsonProcessingException e) {
            System.out.println("jsonArray异常");
        }
    }

    // Json -> List<Object>
    @Test
    void test8() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonArray = "[\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"鸡蛋炒番茄\",\n" +
                "    \"age\": 24,\n" +
                "    \"gender\": \"女\",\n" +
                "    \"address\": \"南极洲\",\n" +
                "    \"phone\": \"13287654329\",\n" +
                "    \"email\": \"13287654329@qq.com\",\n" +
                "    \"password\": \"123456\",\n" +
                "    \"birthday\": 1588262400000\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"番茄炒鸡蛋\",\n" +
                "    \"age\": 13,\n" +
                "    \"gender\": \"男\",\n" +
                "    \"address\": \"非洲\",\n" +
                "    \"phone\": \"13254687954\",\n" +
                "    \"email\": \"13254687954@qq.com\",\n" +
                "    \"password\": \"123456\",\n" +
                "    \"birthday\": 1590940800000\n" +
                "  }\n" +
                "]";
        try {
            System.out.println(mapper.readValue(jsonArray, new TypeReference<List<User>>() {
            }).toString());
        } catch (JsonProcessingException e) {
            System.out.println("jsonArray异常");
        }
    }

    // Json -> Map
    @Test
    void test9() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"小峰\",\n" +
                "  \"age\": 13,\n" +
                "  \"gender\": null,\n" +
                "  \"address\": \"非洲\",\n" +
                "  \"phone\": \"13254687954\",\n" +
                "  \"email\": \"13254687954@qq.com\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"birthday\": 1590940800000\n" +
                "}";
        try {
            System.out.println(mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            }));
        } catch (JsonProcessingException e) {
            System.out.println("json异常");
        }
    }

    // Object -> jsonNode
    @Test
    void test10() {

        ObjectMapper mapper = new ObjectMapper();

        User user = new User();
        user.setId(1L);
        user.setUsername("番茄炒鸡蛋");
        user.setAge(13L);
        user.setGender("男");
        user.setAddress("非洲");
        user.setPhone("13254687954");
        user.setEmail("13254687954@qq.com");
        user.setPassword("123456");
        user.setBirthday(new DateTime(2020, 6, 1, 0, 0, 0).toDate());

        System.out.println(mapper.valueToTree(user));
    }

    // JsonNode -> Object
    @Test
    void test11() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"小峰\",\n" +
                "  \"age\": 13,\n" +
                "  \"gender\": null,\n" +
                "  \"address\": \"非洲\",\n" +
                "  \"phone\": \"13254687954\",\n" +
                "  \"email\": \"13254687954@qq.com\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"birthday\": 1590940800000\n" +
                "}";
        try {
            JsonNode jsonNode = mapper.readTree(json);
            System.out.println(mapper.treeToValue(jsonNode, User.class));
        } catch (JsonProcessingException e) {
            System.out.println("json异常");
        }
    }
}