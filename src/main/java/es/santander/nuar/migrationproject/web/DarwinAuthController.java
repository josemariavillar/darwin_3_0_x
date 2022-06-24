package es.santander.nuar.migrationproject.web;

import es.santander.darwin.common.authorization.types.OperationType;
import es.santander.darwin.common.authorization.types.Scope;
import es.santander.darwin.security.authorization.annotation.ClientId;
import es.santander.darwin.security.authorization.annotation.ContractId;
import es.santander.darwin.security.authorization.annotation.OperativeControl;
import es.santander.darwin.security.authorization.error.MessagesError;
import es.santander.darwin.security.authorization.facade.OCProcessor;
import es.santander.darwin.security.credentials.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RestController
@RequestMapping("/auth")
@Slf4j
public class DarwinAuthController {

	private OCProcessor ocProcessor;

	String var = MessagesError.KEY_PROVIDER_ERROR;

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String sayHello() throws IllegalAccessException {
		throw new IllegalAccessException(MessagesError.KEY_PROVIDER_ERROR);
	}

	@OperativeControl(scope = Scope.CHANNEL, type = OperationType.CONSULTIVE)
	@GetMapping("/getWihtAnnotation")
	public void getWihtAnnotation(@RequestParam @ClientId String param1, @RequestParam String paramExtra,
			@RequestParam @ContractId String param2) {
		log.debug("inside @GetMapping getWihoutAnnotation");
	}

	public Function<Token<Token.TokenType, String>, Mono<Authentication>> updateAuthenticationParameters() {
		return ocProcessor.updateAuthenticationParameters();
	}

	private String getKeyProviderError() {
		return MessagesError.KEY_PROVIDER_ERROR;
	}

}