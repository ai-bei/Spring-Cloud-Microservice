package com.iotknowyou.fallback;

import com.iotknowyou.base.ResponseCode;
import com.iotknowyou.base.ResponseData;
import com.iotknowyou.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


/*
	在 Spring Cloud 中， Zuul 默认整合了 Hystrix ，
	当后端服务异常时可以为 Zuul 添加回 退功能，返回默认的数据给客户端。
	实现回退机制需要实现 ZuulFallbackProvider 接口
*/


/*
	getRoute 方法中返回＊表示对所有服务进行回退操作，如果只想对某个服务进行回退，
	那么就返回需要回退的服务名称，这个名称一定要是注册到 Eureka 中的名称。
	通过 ClientHttpResponse 构造回退的内容；通过 getStatusCode 返回响应的状态码；
	通过 getStatusText 返回响应状态码对应的文本；
	通过 getBody 返回回退的内容；
	通过 getHeaders 返回响应的请求头信息。


*/
@Component
public class ServiceConsumerFallbackProvider implements FallbackProvider {
	
	private Logger log = LoggerFactory.getLogger(ServiceConsumerFallbackProvider.class);

	@Override
	public String getRoute() {
		return "*";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return this.getStatusCode().value();
			}

			@Override
			public String getStatusText() throws IOException {
				return this.getStatusCode().getReasonPhrase();
			}

			@Override
			public void close() {

			}

			@Override
			public InputStream getBody() throws IOException {
				if (cause != null) {
					log.error("", cause.getCause());
				}
				ResponseData data = ResponseData.fail(route+"服务内部错误", ResponseCode.SERVER_ERROR_CODE.getCode());
				return new ByteArrayInputStream(JsonUtils.toJson(data).getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
				headers.setContentType(mt);
				return headers;
			}
		};
	}
}
