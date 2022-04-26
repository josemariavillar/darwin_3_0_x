package es.santander.nuar.migrationproject.web;

import es.santander.darwin.common.context.DarwinContextImpl;
import es.santander.darwin.common.annotation.CustomErrorModel;
import es.santander.darwin.common.authorization.AuthorizationData;
import es.santander.darwin.common.authorization.Contract;
import es.santander.darwin.common.authorization.Contrato;
import es.santander.darwin.common.authorization.types.OperationType;
import es.santander.darwin.common.authorization.types.Scope;
import es.santander.darwin.common.constants.Constants;
import es.santander.darwin.common.context.DarwinContext;
import es.santander.darwin.common.context.DarwinContextHolder;
import es.santander.darwin.common.exceptions.HttpBaseDarwinException;
import es.santander.darwin.common.http.ResettableStreamHttpServletRequest;
import es.santander.darwin.common.omnichannel.ContactPoint;
import es.santander.darwin.common.support.ServerWebExchangeUtils;
import es.santander.darwin.common.ws.SoapConstants;
import es.santander.darwin.common.ws.bean.MarcoChannelsBean;
import es.santander.darwin.common.ws.bean.ResourcesBean;
import es.santander.darwin.omnichannel.util.ContactPointHelper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/common")
public class DarwinCommonController {

	String securityUri = SoapConstants.URI_SECURITY_DEFAULT;

	String var1 = Constants.CLIENT_ID;

	Contract contract;

	Contrato contrato;

	ResourcesBean resources;

	ServerWebExchange exchange;

	ServerWebExchange serverWebExchange;

	HttpServletRequest request;

	ContactPointHelper contactPointHelper;

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello() {
		return "Hello world!";
	}

	@GetMapping(value = "/http-base-darwin-exception")
	public RuntimeException getHttpBaseDarwinException() {
		return new HttpBaseDarwinException("", HttpStatus.INTERNAL_SERVER_ERROR, "", "");
	}

	@GetMapping("/get-contactpoint")
	public ContactPoint getDarwinContactPoint() {
		DarwinContext context = DarwinContextHolder.getCurrentContext();
		return context.getContactPoint();
	}

	@GetMapping("/get-returnDarwinContextImpl")
	public DarwinContext getDarwinContextImpl() {
		return new DarwinContextImpl();
	}

	@GetMapping("/get-setContestDarwinContextImpl")
	public DarwinContext setDarwinContextImplToContext() {
		DarwinContextHolder.setCurrentContext(new DarwinContextImpl());
		return DarwinContextHolder.getCurrentContext();
	}

	@GetMapping("/get-contactpointHelper")
	public ContactPoint getDarwinContactPointHelper() {
		return contactPointHelper.getContactPoint();
	}

	@GetMapping("/get-authorizationData")
	public AuthorizationData getDarwinAuthorizationData() {
		return AuthorizationData.builder().scope(Scope.CHANNEL).operationType(OperationType.OPERATIVE).contract(false)
				.client(false).channel("OFI").build();
	}

	@GetMapping("/get-request")
	public ResettableStreamHttpServletRequest getResettableStream() {
		request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.PAYMENT_REQUIRED.value());
		return new ResettableStreamHttpServletRequest(request);
	}

	@GetMapping("/get-contract")
	public Contract getDarwinMultiEntityCORequest() {
		contract = new Contract();
		contract.setCompany("A.C.M.E");
		contract.setCenter("CT");
		contract.setProduct("Wathever");
		contract.setNumContract("someNumberContract");
		return contract;
	}

	@GetMapping("/get-contrato")
	public Contrato getDarwinCONRequest() {
		contrato = new Contrato();
		contrato.setEmpresa("T.I.A");
		contrato.setCentro("CT");
		contrato.setProducto("Cualquiera");
		contrato.setNumeroContrato("algúnNúmeroDeContrato");
		return contrato;
	}

	public void getDarwinConfigBean() {
		resources = new ResourcesBean();
		resources.setDefaultEndpoint("http://.../Default");

		Map<String, MarcoChannelsBean> marcoChannels = new HashMap<>();
		marcoChannels.put("INT", new MarcoChannelsBean("http://.../INT"));
		marcoChannels.put("OFI", new MarcoChannelsBean("http://.../OFI"));
		resources.setMarcoChannels(marcoChannels);
	}

	public Mono<Object> someMethod() {
		return ServerWebExchangeUtils.cacheRequestBody(exchange,
				serverHttpRequest -> Mono.just(serverWebExchange.mutate().request(serverHttpRequest).build()));
	}

	@CustomErrorModel(properties = "error")
	public class ErrorModelExample {

		private int code;

		private String error;

	}

}
