package es.santander.nuar.migrationproject.web;

import es.santander.darwin.security.authorization.error.MessagesError;
import es.santander.darwin.security.authorization.facade.OCProcessor;
import es.santander.darwin.security.credentials.Token;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RestController
@RequestMapping("/auth")
public class DarwinAuthController {

	private OCProcessor ocProcessor;

	String var = MessagesError.KEY_PROVIDER_ERROR;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String sayHello() throws IllegalAccessException {
		throw new IllegalAccessException(MessagesError.KEY_PROVIDER_ERROR);
	}

	public Function<Token<Token.TokenType, String>, Mono<Authentication>> updateAuthenticationParameters() {
		return ocProcessor.updateAuthenticationParameters();
	}

	private String getKeyProviderError() {
		return MessagesError.KEY_PROVIDER_ERROR;
	}

}