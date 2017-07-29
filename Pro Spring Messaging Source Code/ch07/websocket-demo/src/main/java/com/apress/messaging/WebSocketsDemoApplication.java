package com.apress.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WebSocketsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketsDemoApplication.class, args);
	}
	
	/* Enable this code if you want to send a Message to a WebSocket server using Spring low-level WebSocket classes */
	
	/*
	@Bean
	CommandLineRunner sendLlws(LlWebSocketHandler handler){
		return args -> {
			StandardWebSocketClient client = new StandardWebSocketClient();
			ListenableFuture<WebSocketSession> future = client.doHandshake(handler,  new WebSocketHttpHeaders(), new URI("ws://localhost:8080/llws"));
			WebSocketSession session = future.get();
			
			WebSocketMessage<String> message = new TextMessage("Hello there...");
			session.sendMessage(message);
		};
	}
	*/
	
	/* Enable this if you need to use a WebSocket client from a Code using SockJS/STOMP */
	
	/*
	@Bean
	CommandLineRunner send(SimpleWebSocketsProperties props){
		return args -> {
			String empty = "";
			String url = "ws://localhost:8080" + props.getEndpoint();
			
			List<Transport> transports = Arrays.asList( 
				    new WebSocketTransport(new StandardWebSocketClient()), 
				    new RestTemplateXhrTransport(new RestTemplate())); 
			
			
			SockJsClient sockJsClient = new SockJsClient(transports); 
			WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient); 
			StompSessionHandler handler = new StompSessionHandlerAdapter() { 
				 //@Override 
				 //  public void afterConnected(StompSession session, 
				 //    StompHeaders connectedHeaders){
				 //	   System.out.println("#### >>> ");
				 //	 
				 //}
			 };
			
			stompClient.setMessageConverter(new MappingJackson2MessageConverter()); 
			ListenableFuture<StompSession> future = stompClient.connect(url, handler, empty);
			
			StompSession session = future.get();
			session.send(props.getAppDestinationPrefix() + props.getMapping(), new ChatMessage("Hello there..."));
			
			
			//future.addCallback(
			//		new SuccessCallback<StompSession>() {
			//				public void onSuccess(StompSession stompSession) {
			//					System.out.println(">>> on Success!");
			//				}
			//		},
			//		new FailureCallback() {
			//			    public void onFailure(Throwable throwable) {
			//			        System.out.println(">>> on Failure!");
			//			        throwable.printStackTrace();
			//			    }
			//		}
			//);
			
		};
	}
	
	*/
}
