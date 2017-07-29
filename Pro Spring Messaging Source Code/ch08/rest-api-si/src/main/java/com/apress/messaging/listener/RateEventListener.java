package com.apress.messaging.listener;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.apress.messaging.annotation.Log;
import com.apress.messaging.event.CurrencyEvent;

@Component
public class RateEventListener {

	//@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	@TransactionalEventListener
	@Log(printParamsValues=true,callMethodWithNoParamsToString="getRate")
	public void processEvent(CurrencyEvent event){ }
}
