package es.santander.nuar.migrationproject.config;

import es.santander.darwin.common.security.auth.DarwinUserDetails;
import es.santander.darwin.security.authentication.config.AuthenticationProperties;
import es.santander.darwin.security.authentication.config.AuthenticationReactiveSTSAutoConfig;
import es.santander.darwin.security.authentication.converter.TokenConverter;
import es.santander.darwin.security.authentication.interceptor.JWTFilterFunction;
import es.santander.darwin.security.authentication.interceptor.JWTInterceptor;
import es.santander.darwin.security.authentication.interceptor.JWTReactiveFilterFunction;
import es.santander.darwin.security.authentication.interceptor.JWTServletFilterFunction;
import es.santander.darwin.security.authentication.reactive.decorator.ServerWebExchangeDecoratorImpl;
import es.santander.darwin.security.authentication.reactive.error.AuthenticationError;
import es.santander.darwin.security.authentication.reactive.filter.authentication.AuthenticationBearerToken;
import es.santander.darwin.security.authentication.reactive.filter.authentication.AuthenticationReactiveManager;
import es.santander.darwin.security.authentication.reactive.filter.authentication.DefaultToken;
import es.santander.darwin.security.authentication.reactive.filter.authentication.ReactiveAuthenticationConverter;
import es.santander.darwin.security.authentication.reactive.filter.authentication.ReactiveAuthenticationSuccessHandler;
import es.santander.darwin.security.authentication.reactive.filter.authentication.UnauthorizedGatewayEntryPoint;
import es.santander.darwin.security.authentication.reactive.filter.authentication.UnauthorizedMicroserviceEntryPoint;
import es.santander.darwin.security.authentication.service.SecurityService;
import es.santander.darwin.security.authentication.service.SecurityServiceImpl;
import es.santander.darwin.security.authentication.service.SecurityServiceReactiveImpl;
import es.santander.darwin.security.authentication.validator.TokenAuthenticationValidator;
import es.santander.darwin.security.authentication.validator.TokenAuthenticationValidatorImpl;
import es.santander.darwin.security.credentials.Token;
import es.santander.darwin.security.credentials.bks.config.BKSTokenProperties;
import es.santander.darwin.security.credentials.bks.credential.BKSToken;
import es.santander.darwin.security.credentials.bks.credential.BKSValidatableCredential;
import es.santander.darwin.security.credentials.jwt.util.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public class AuthenticationServive {

	@Autowired
	TokenConverter tc;

	@Autowired
	DarwinUserDetails dud;

	@Autowired
	SecurityService<Authentication, Token> ss;

	/**
	 * Attributes
	 */

	private Token t;

	private BKSValidatableCredential bksV;

	private BKSToken bksT;

	private JWTTokenUtil jwtTU;

	private AuthenticationReactiveSTSAutoConfig authReacSTSC;

	private JWTInterceptor jwtI;

	private JWTFilterFunction jwtFF;

	private JWTServletFilterFunction jwtSFF;

	private JWTReactiveFilterFunction jwtRFF;

	private ServerWebExchangeDecoratorImpl swedi;

	private AuthenticationError auError;

	private AuthenticationBearerToken auBT;

	private AuthenticationReactiveManager arm;

	private DefaultToken det;

	private ReactiveAuthenticationConverter rac;

	private ReactiveAuthenticationSuccessHandler tash;

	private UnauthorizedGatewayEntryPoint ugep;

	private UnauthorizedMicroserviceEntryPoint umsep;

	private SecurityService sservice;

	private SecurityServiceImpl ssi;

	private SecurityServiceReactiveImpl ssri;

	private AuthenticationProperties ap;

	private TokenAuthenticationValidator tav;

	private TokenAuthenticationValidatorImpl tavi;

	public String defaultSignatureAlgorithm = BKSTokenProperties.DEFAULT_SIGNATURE_ALGORITHM;

	public void reviewAuth() {

		Optional<String> t1 = tc.getCorpTokenAndSaveIt();
		Optional<String> t2 = tc.getCorpTokenFromSecurityContext();
		Optional<String> t3 = tc.getJwtTokenAndSaveIt();
		Optional<String> t4 = tc.getJwtTokenFromSecurityContext();
		Optional<String> t5 = tc.getCorpTokenFromHeaders();
		String t6 = tc.getCorpToken();
		String t7 = tc.getJwtToken();

		Optional<String> dud1 = dud.getCorpToken();
		Optional<String> dud2 = dud.getJwtToken();
	}

}
