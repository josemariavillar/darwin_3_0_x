package es.santander.nuar.migrationproject.web;

import com.santander.saga.sagacity_common_darwin_adapter.service.TranslateService;
import com.santander.saga.sagacity_darwin_adapter.controller.DarwinArchitectureController;
import com.santander.saga.sagacity_fwk_starter.model.SagaResult;
import com.santander.saga.sagacity_fwk_starter.model.SagaWorkflow;
import com.santander.saga.sagacity_fwk_starter.service.SagaService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saga")
public class DarwinSagacityController extends DarwinArchitectureController {

	@Autowired
	private ObjectFactory<SagaWorkflow> sagaWorkflow;

	/**
	 * Saga service
	 */
	private SagaService sagaService;

	private TranslateService translateService;

	public DarwinSagacityController(SagaService sagaService, TranslateService translateService) {
		super(sagaService, translateService);
	}

	@RequestMapping("/ok")
	public SagaResult launchSaga() {
		return startSaga(sagaWorkflow.getObject(), 1000000L, 1000L);
	}

}