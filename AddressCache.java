package com.agoda.cache;

import java.net.InetAddress;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/*
 * The AddressCache has a max age for the elements it's storing, an add method 
 * for adding elements, a remove method for removing, a peek method which 
 * returns the most recently added element, and a take method which removes 
 * and returns the most recently added element.
 */
public class AddressCache {
	
	private static final Logger log=Logger.getLogger(AddressCache.class.getName());

	long maxAge;
	TimeUnit unit;

	Stack<InetAddress> stack;

	public AddressCache(long maxAge, TimeUnit unit) {
		this.maxAge = maxAge;
		this.unit = unit;
		stack = new Stack<>();
	}

	/**
	 * add() method must store unique elements only (existing elements must be
	 * ignored). This will return true if the element was successfully added.
	 * 
	 * @param address
	 * @return
	 */

	public synchronized boolean add(InetAddress address) {
		try {
			if (address != null && !stack.contains(address)) {
				stack.push(address);
				return true;
			}
		} catch (StackOverflowError e) {
			log.info("Stack OverFlow Exception:" + e.getMessage());
		}catch(Exception e){
			log.info("Exception in adding element:" + e.getMessage());
		}
		return false;
	}

	/**
	 * remove() method will return true if the address was successfully removed
	 * 
	 * @param address
	 * @return
	 */
	public synchronized boolean remove(InetAddress address) {
		try {
			if (!stack.isEmpty() && address!=null && stack.contains(address)) {
				stack.remove(address);
				return true;
			}
		} catch (Exception e) {
			log.info("Exception in removing element:" + e.getMessage());
		}
		return false;
	}

	/**
	 * The peek() method will return the most recently added element, null if no
	 * element exists.
	 * 
	 * @return
	 */
	public synchronized InetAddress peek() {
		try {
			if (!stack.isEmpty()) {
				return stack.peek();
			}
		} catch (Exception e) {
			log.info("Exception in getting top element:" + e.getMessage());
		}
		return null;
	}

	/**
	 * take() method retrieves and removes the most recently added element from
	 * the cache and waits if necessary until an element becomes available.
	 * 
	 * @return
	 */
	public synchronized InetAddress take() {
		try {
			if (!stack.isEmpty()) {
				return stack.pop();
			}
		} catch (Exception e) {
			log.info("Exception in popping top element:" + e.getMessage());
		}
		return null;
	}

}
