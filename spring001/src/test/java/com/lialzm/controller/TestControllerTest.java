package com.lialzm.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import javax.servlet.http.Cookie;
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
                .andDo(MockMvcResultHandlers.print());
        return ra;
    }

    private String getResult(ResultActions ra) throws Exception {
        MvcResult mr = ra.andReturn();
        String result = mr.getResponse().getContentAsString();
        return result;
    }

    private String postForm(String url) throws Exception {
        return postForm(url, new HashMap<String, String>());
    }

    private String postForm(String url, Map<String, String> params) throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post(url);
        return getResult(requestForm(mockHttpServletRequestBuilder, params));
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
    public void getUserModelTest() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        String result = postForm("/getUserModel.do", map);
        System.out.println(result);
    }

    @Test
    public void clearSessionTest() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String result = postForm("/clearSession.do", map);
        System.out.println(result);
    }

    public void setSessionTest() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/setSession.do");
        mockHttpServletRequestBuilder.cookie(new Cookie("JSESSIONID", "AAAAAA"));
        ResultActions resultActions = requestForm(mockHttpServletRequestBuilder, new HashMap<String, String>());
        String result = getResult(resultActions);
    }

    @Test
    public void getSessionTest() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/getSession.do");
        mockHttpServletRequestBuilder.cookie(new Cookie("JSESSIONID", "AAAAAA"));
        String result = getResult(requestForm(mockHttpServletRequestBuilder, new HashMap<String, String>()));
    }

    @Test
    public void sessionTest() throws Exception {
        setSessionTest();
        getSessionTest();
    }

}
