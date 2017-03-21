package com.lialzm.spring.support;

import com.jayway.jsonpath.JsonPath;
import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.http.*;
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
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

/**
 * Created by A on 2017/3/21.
 */
public class JsonRequestMethodArgumentResolver extends AbstractMessageConverterMethodArgumentResolver {

    private static final Object NO_VALUE = new Object();

    private static final Set<HttpMethod> SUPPORTED_METHODS =
            EnumSet.of(HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH);


    public JsonRequestMethodArgumentResolver(List<HttpMessageConverter<?>> converters) {

        super(converters);
    }

    public JsonRequestMethodArgumentResolver(List<HttpMessageConverter<?>> converters, List<Object> requestResponseBodyAdvice) {
        super(converters, requestResponseBodyAdvice);
    }


    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonRequest.class);
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object arg = readWithMessageConverters(webRequest, parameter, parameter.getGenericParameterType());
        return arg;
    }

    @Override
    protected <T> Object readWithMessageConverters(HttpInputMessage inputMessage, MethodParameter param, Type targetType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {

        MediaType contentType;
        boolean noContentType = false;
        try {
            contentType = inputMessage.getHeaders().getContentType();
        } catch (InvalidMediaTypeException ex) {
            throw new HttpMediaTypeNotSupportedException(ex.getMessage());
        }
        if (contentType == null) {
            noContentType = true;
            contentType = MediaType.APPLICATION_OCTET_STREAM;
        }

        Class<?> contextClass = (param != null ? param.getContainingClass() : null);
        Class<T> targetClass = (targetType instanceof Class<?> ? (Class<T>) targetType : null);
        if (targetClass == null) {
            ResolvableType resolvableType = (param != null ?
                    ResolvableType.forMethodParameter(param) : ResolvableType.forType(targetType));
            targetClass = (Class<T>) resolvableType.resolve();
        }
        HttpMethod httpMethod = ((HttpRequest) inputMessage).getMethod();
        Object body = NO_VALUE;
        try {
            inputMessage = new EmptyBodyCheckingHttpInputMessage(inputMessage);
            String json = StreamUtils.copyToString(inputMessage.getBody(), Charset.defaultCharset());
            JsonRequest jsonRequest = param.getParameterAnnotation(JsonRequest.class);
            return JsonPath.read(json, jsonRequest.value());
        } catch (IOException ex) {
            throw new HttpMessageNotReadableException("Could not read document: " + ex.getMessage(), ex);
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
