package com.agoda.cache;

import static org.junit.Assert.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AddressCacheTest {
    
	long duration=2;
	TimeUnit unit=TimeUnit.MILLISECONDS;
	AddressCache addressCache ;
	
	@Before
	public void setUp() throws Exception {
		addressCache= new AddressCache(duration,unit);
	}

	@After
	public void tearDown() throws Exception {
		addressCache=null;
	}

	@Test
	public void testAddWhenOneAddressAdded() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		addressCache.add(address);
		assertEquals(1, addressCache.stack.size());
		
	}
	
	@Test
	public void testAddWhenSameElementAdded() throws UnknownHostException  {
		InetAddress address = InetAddress.getLocalHost();
		addressCache.add(address);
		addressCache.add(address);
		assertEquals(1, addressCache.stack.size());
		assertFalse(addressCache.add(address));
		
	}
	
	@Test
	public void testAddWhenNullAdded()  {
		assertFalse(addressCache.add(null));
		
	}

	@Test
	public void testRemoveWhenNullRemoved() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		addressCache.add(address);
		assertFalse(addressCache.remove(null));
	}
	
	@Test
	public void testRemoveWhenAddressRemoved() throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		InetAddress address1 = InetAddress.getByName("340amit.wordpress.com");
		addressCache.add(address);
		addressCache.add(address1);
		assertEquals(2, addressCache.stack.size());
		addressCache.remove(address);
		assertEquals(1, addressCache.stack.size());
		assertEquals(address1, addressCache.peek());
	}

	@Test
	public void testPeekWhenNoElement(){
		assertEquals(null, addressCache.peek());
	}
	
	@Test
	public void testPeekWhenTwoElementAdded() throws UnknownHostException{
		InetAddress address = InetAddress.getLocalHost();
		InetAddress address1 = InetAddress.getByName("340amit.wordpress.com");
		addressCache.add(address);
		addressCache.add(address1);
		assertEquals(address1, addressCache.peek());
	}
	
	@Test
	public void testTakeWhenNoElement() throws UnknownHostException {
		assertEquals(null, addressCache.take());
	}

	@Test
	public void testTakeWhenAddressPopped() throws UnknownHostException{
		InetAddress address = InetAddress.getLocalHost();
		InetAddress address1 = InetAddress.getByName("340amit.wordpress.com");
		addressCache.add(address);
		addressCache.add(address1);
		assertEquals(2, addressCache.stack.size());
		addressCache.take();
		assertEquals(1, addressCache.stack.size());
		assertEquals(address, addressCache.peek());
	}

}
