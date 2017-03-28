package com.lialzm;

import com.lialzm.controller.TestController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.HttpSessionRequiredException;

import javax.servlet.http.Cookie;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by A on 2017/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-applicationContext.xml", "classpath:spring-mvc.xml"})
@WebAppConfiguration
public class TestControllerTest {
    private MockMvc mockMvc;

    @Autowired
    TestController testController;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
    }

    private String getForm(String url, Map<String, String> params) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(url);
        return getResult(requestForm(mockHttpServletRequestBuilder, params));
    }

    private ResultActions requestForm(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, Map<String, String> params) throws Exception {
        mockHttpServletRequestBuilder
                .accept(MediaType.MULTIPART_FORM_DATA)
                .characterEncoding("UTF-8");
        Set<String> set = params.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = params.get(key);
            mockHttpServletRequestBuilder.param(key, value);
        }
        ResultActions ra = this.mockMvc
                .perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        return ra;
    }

    private ResultActions requestJson(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, String content) throws Exception {
        mockHttpServletRequestBuilder
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .characterEncoding("UTF-8").content(content);
        ResultActions ra = this.mockMvc
                .perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        return ra;
    }

    private ResultActions requestFile(MockHttpServletRequestBuilder mockHttpServletRequestBuilder, byte[] bytes) throws Exception {
        mockHttpServletRequestBuilder
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .characterEncoding("UTF-8");
        ResultActions ra = this.mockMvc
                .perform(mockHttpServletRequestBuilder)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        return ra;
    }

    private String getResult(ResultActions ra) throws Exception {
        MvcResult mr = ra.andReturn();
        String result = mr.getResponse().getContentAsString();
        return result;
    }

    private String postFile(String url, byte[] bytes) throws Exception {
        String json = "{\n" +
                "\t\"id\":\"123\"\n" +
                "}";
        MockHttpServletRequestBuilder fileRequestBuilder =
                MockMvcRequestBuilders.fileUpload(url).file("image",bytes);
        return getResult(requestFile(fileRequestBuilder, bytes));
    }

    private String postForm(String url) throws Exception {
        return postForm(url, new HashMap<String, String>());
    }

    private String postForm(String url, Map<String, String> params) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(url);
        return getResult(requestForm(mockHttpServletRequestBuilder, params));
    }

    private String postJson(String url, String content) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(url);
        return getResult(requestJson(mockHttpServletRequestBuilder, content));
    }

    @Test
    public void postForm() throws Exception {
        String name = "li lee";
        String age = "11";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("age", age);
        String result = postForm("/form.do", map);
        Assert.assertEquals(name + "," + age, result);
        System.out.println(result);
    }

    @Test
    public void getForm() throws Exception {
        String name = "li lee";
        String age = "11";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("age", age);
        String result = getForm("/form.do", map);
        Assert.assertEquals(name + "," + age, result);
        System.out.println(result);
    }

    @Test
    public void requestParamValueTest() throws Exception {
        String name = "li lee";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        String result = postForm("/requestParamValue.do", map);
    }

    @Test
    public void form2() throws Exception {
        String name = "li lee";
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        String result = postForm("/form2.do", map);
        Assert.assertEquals(name, result);
    }

    /**
     * 缺少参数返回400
     *
     * @throws Exception
     */
    @Test
    public void form3() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/form2.do");
        Map<String, String> map = new HashMap<String, String>();
        getResult(requestForm(mockHttpServletRequestBuilder, map).andExpect(status().is(400)));
    }

    @Test
    public void form4() throws Exception {
        postForm("/form3.do", new HashMap<String, String>());
    }

    @Test
    public void getUserTest() throws Exception {
        String result = postForm("/user/1");
        Assert.assertEquals("1", result);
    }

    @Test
    public void getCookieTest() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/getCookie.do");
        mockHttpServletRequestBuilder.cookie(new Cookie("JSESSIONID", "AAAAAA"));
        String result = getResult(requestForm(mockHttpServletRequestBuilder, new HashMap<String, String>()));
        Assert.assertEquals("AAAAAA", result);
    }

    @Test
    public void namesTest() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "张三,李四");
        getForm("/names.do", map);
    }

    @Test
    public void getHeadTest() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/getHead.do");
        mockHttpServletRequestBuilder.header("userId", "11");
        String result = getResult(requestForm(mockHttpServletRequestBuilder, new HashMap<String, String>()));
        Assert.assertEquals("11", result);
    }


    @Test
    public void getUserByModelTest() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "123");
        map.put("role.id", "444");
        postForm("/getUserByModel.do", map);
    }

    @Test
    public void getRoleModelTest() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        String result = postForm("/getRoleModel.do", map);
        System.out.println(result);
    }

    @Test
    public void clearSessionTest() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/clearSession.do");
        mockHttpServletRequestBuilder.session(session);
        ResultActions resultActions = requestForm(mockHttpServletRequestBuilder, new HashMap<String, String>());
        String result = getResult(resultActions);
    }


    MockHttpSession session = new MockHttpSession(new MockServletContext(), "1");

    @Test
    public void setSessionTest() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/setSession.do");
        mockHttpServletRequestBuilder.session(session);
        ResultActions resultActions = requestForm(mockHttpServletRequestBuilder, new HashMap<String, String>());
        String result = getResult(resultActions);
    }

    @Test
    public void getSessionTest() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/getSession.do");
        mockHttpServletRequestBuilder.session(session);
        String result = getResult(requestForm(mockHttpServletRequestBuilder, new HashMap<String, String>()));
    }

    @Test(expected = HttpSessionRequiredException.class)
    public void sessionTest() throws Exception {
        setSessionTest();
        getSessionTest();
        clearSessionTest();
        getSessionTest();
    }

    @Test
    public void getUserByJsonTest() throws Exception {
        String json = "{\n" +
                "\t\"id\":\"123\"\n" +
                "}";
        postJson("/getUserByJson.do", json);
    }

    @Test
    public void getFileTest() throws Exception {
        String path = new File("").getAbsolutePath();
        path = path + File.separator + "src" +
                File.separator + "test" + File.separator + "resources" + File.separator + "text.txt";
        InputStream inputStream = new FileInputStream(path);
        MockMultipartFile file = new MockMultipartFile("file", inputStream);
        postFile("/getFile.do", file.getBytes());
    }

}
