package com.apress.messaging.aop;

import java.lang.reflect.Parameter;
import java.util.stream.IntStream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class JMSAudit {
	private static final String DASH_LINE = "===================================";
	private static final String NEXT_LINE = "\n";
	private static final Logger log =LoggerFactory.getLogger("JMSAudit");
	
	@Pointcut("execution(* com.apress.messaging.jms.*.*(..))")
	public void logJms(){};
	
	@Around("logJms()")
	public Object jmsAudit(ProceedingJoinPoint pjp) throws Throwable{
		Object[] args = pjp.getArgs();
		Parameter[]  parameters = ((MethodSignature)pjp.getSignature()).getMethod().getParameters();
		
		StringBuilder builder = new StringBuilder(NEXT_LINE);
		builder.append(DASH_LINE);
		builder.append(NEXT_LINE);
		builder.append("[BEFORE]");
		builder.append(NEXT_LINE);
		builder.append("Method: ");
		builder.append(pjp.getSignature().getName());
		builder.append(NEXT_LINE);
		builder.append("Params: ");
		builder.append(NEXT_LINE);
		IntStream.range(0,args.length).forEach(index -> {
			builder.append("> ");
			builder.append(parameters[index].getName());
			builder.append(": ");
			builder.append(args[index]);
			builder.append(NEXT_LINE);
		});
		builder.append(DASH_LINE);
		log.info(builder.toString());
		
		Object object = pjp.proceed(args);
		
		//Some Extra logging [AFTER]
		
		return object;
	}
}
