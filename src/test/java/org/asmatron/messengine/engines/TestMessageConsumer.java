package org.asmatron.messengine.engines;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.asmatron.messengine.engines.components.MessageConsumer;
import org.asmatron.messengine.messaging.MessageListener;
import org.asmatron.messengine.testing.TestMessage;
import org.junit.Test;



public class TestMessageConsumer {

	@SuppressWarnings("unchecked")
	@Test
	public void shouldConsumeAMessage() throws Exception {
		
		List<MessageListener<TestMessage<String>>> listeners = new ArrayList<MessageListener<TestMessage<String>>>();
		MessageListener<TestMessage<String>> listener = mock(MessageListener.class);
		listeners.add(listener );
		TestMessage<String> message = new TestMessage<String>("type", "body");
		MessageConsumer<TestMessage<String>>  consumer = new MessageConsumer<TestMessage<String>>(message , listeners);
		
		assertNotNull(consumer);
		assertTrue(consumer instanceof Runnable);
		
		consumer.run();
		
		verify(listener).onMessage(message);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void shouldHandleListenerErrors() throws Exception {
		
		List<MessageListener<TestMessage<String>>> listeners = new ArrayList<MessageListener<TestMessage<String>>>();
		MessageListener<TestMessage<String>> listener = mock(MessageListener.class);
		listeners.add(listener );
		TestMessage<String> message = new TestMessage<String>("type", "body");
		MessageConsumer<TestMessage<String>>  consumer = new MessageConsumer<TestMessage<String>>(message , listeners);
		
		assertNotNull(consumer);
		assertTrue(consumer instanceof Runnable);
		
		doThrow(new NullPointerException("Some unexpected exception")).when(listener).onMessage(message);
		
		consumer.run();
		
		verify(listener).onMessage(message);
	}

}
