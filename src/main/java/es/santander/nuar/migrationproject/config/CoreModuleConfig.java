package es.santander.nuar.migrationproject.config;

import es.santander.darwin.common.exceptions.DarwinExceptionCode;
import es.santander.darwin.common.support.GenericUtils;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.Connection;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

public class CoreModuleConfig {

	@Autowired
	@Qualifier("isReactiveType")
	Boolean isReactiveType;

	GenericUtils utils;

	DarwinExceptionCode e = DarwinExceptionCode.STS_CONVERSION_FAILED;

	@Autowired
	private WebClient.Builder webClientBuilder;

	public void init() {
		String result = utils.escapeCRLF("init");
		WebClient webClient = customizeWebClient();
	}

	private WebClient customizeWebClient() {
		TcpClient tcpClient = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 50)
				.doOnConnected((Connection connection) -> connection
						.addHandlerLast(new ReadTimeoutHandler(50, TimeUnit.MILLISECONDS))
						.addHandlerLast(new WriteTimeoutHandler(50, TimeUnit.MILLISECONDS)));
		return webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient))).build();
	}

	private String getDescriptionError() {
		String desc = e.getErrorName() + e.getCode().toString();
		return desc;
	}

}
