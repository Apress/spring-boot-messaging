package com.apress.messaging.config;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.apress.messaging.handler.PersonHandler;

import reactor.ipc.netty.http.server.HttpServer;

@Configuration
public class ServerConfig {

	PersonHandler handler;
	ServerConfig(PersonHandler handler){
		this.handler = handler;
	}

	@Bean
	RouterFunction<ServerResponse> router(){
		return RouterFunctions
				.route(GET("/persons/{id}").and(accept(APPLICATION_JSON)), handler::findById)
				.andRoute(GET("/persons").and(accept(APPLICATION_JSON)), handler::findAll)
				.andRoute(GET("/person-watcher").and(accept(APPLICATION_JSON)), handler::newPersonLog)
				.andRoute(POST("/persons").and(accept(APPLICATION_JSON)), handler::createPerson);
	}
	
	@Bean
	HttpServer httpServer(RouterFunction<ServerResponse> router){
		HttpHandler httpHandler = RouterFunctions.toHttpHandler(router);
		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
		HttpServer server = HttpServer.create("localhost", 8080);
		server.newHandler(adapter).block();
		return server;
	}
}
