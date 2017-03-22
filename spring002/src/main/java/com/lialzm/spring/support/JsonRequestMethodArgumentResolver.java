package com.lialzm.spring.support;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StreamUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by A on 2017/3/21.
 */
public class JsonRequestMethodArgumentResolver extends AbstractMessageConverterMethodArgumentResolver {


    private Logger logger = LoggerFactory.getLogger(getClass());

    //存储json
    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();


    public JsonRequestMethodArgumentResolver(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    public JsonRequestMethodArgumentResolver(List<HttpMessageConverter<?>> converters, List<Object> requestResponseBodyAdvice) {
        super(converters, requestResponseBodyAdvice);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonRequest.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object arg = readWithMessageConverters(webRequest, parameter, parameter.getGenericParameterType());
        //校验是否必填
        if (arg == null) {
            if (checkRequired(parameter)) {
                throw new PathNotFoundException("Required request body is missing: " +
                        parameter.getParameterName());
            }
        }
        return arg;
    }

    protected boolean checkRequired(MethodParameter methodParam) {
        return methodParam.getParameterAnnotation(JsonRequest.class).required();
    }

    @Override
    protected <T> Object readWithMessageConverters(HttpInputMessage inputMessage, MethodParameter param, Type targetType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {
        String currentThreadName = Thread.currentThread().getName();
        long id = Thread.currentThread().getId();
        if (logger.isDebugEnabled()) {
            System.out.println(currentThreadName + id + " is running!");
        }
        try {
            inputMessage = new EmptyBodyCheckingHttpInputMessage(inputMessage);
            InputStream inputStream = inputMessage.getBody();
            String json;
            if (inputStream == null) {
                json = threadLocal.get();
            } else {
                json = StreamUtils.copyToString(inputStream, Charset.defaultCharset());
                threadLocal.set(json);
            }
            JsonRequest jsonRequest = param.getParameterAnnotation(JsonRequest.class);
            //类型转换
            return JsonPath.read(json, jsonRequest.value());
        } catch (PathNotFoundException ex) {
            return null;
        }
    }

    private static class EmptyBodyCheckingHttpInputMessage implements HttpInputMessage {

        private final HttpHeaders headers;

        private final InputStream body;

        private final HttpMethod method;


        public EmptyBodyCheckingHttpInputMessage(HttpInputMessage inputMessage) throws IOException {
            this.headers = inputMessage.getHeaders();
            InputStream inputStream = inputMessage.getBody();
            if (inputStream == null) {
                this.body = null;
            } else if (inputStream.markSupported()) {
                inputStream.mark(1);
                this.body = (inputStream.read() != -1 ? inputStream : null);
                inputStream.reset();
            } else {
                PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
                int b = pushbackInputStream.read();
                if (b == -1) {
                    this.body = null;
                } else {
                    this.body = pushbackInputStream;
                    pushbackInputStream.unread(b);
                }
            }
            this.method = ((HttpRequest) inputMessage).getMethod();
        }

        public HttpHeaders getHeaders() {
            return this.headers;
        }

        public InputStream getBody() throws IOException {
            return this.body;
        }

        public HttpMethod getMethod() {
            return this.method;
        }
    }

}
