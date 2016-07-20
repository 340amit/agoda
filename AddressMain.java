package com.agoda.cache;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class AddressMain {

	public static void main(String[] args){
		
		try{
			
			long duration=2;
			TimeUnit unit=TimeUnit.MILLISECONDS;
			AddressCache a=new AddressCache(duration, unit);
			
			InetAddress address = InetAddress.getLocalHost(); 
			InetAddress address1 = InetAddress.getByName("340amit.wordpress.com");
			InetAddress address2 = InetAddress.getByName("yahoo.com");
			InetAddress address3 = InetAddress.getByName("google.com");
			InetAddress address4 = InetAddress.getByName("340amit.blogspot.com");
			InetAddress address5 = InetAddress.getByName("avinash.blogspot.com");
			InetAddress address6 = InetAddress.getByName("rediff.com");
			InetAddress address7 = null;
			
			
			Thread t1=new Thread(){
				@Override
				public void run(){
					System.out.println(address+" added status : "+a.add(address));
					System.out.println(address1+" added status : "+a.add(address1));
					System.out.println(address2+" added status : "+a.add(address2));
					System.out.println(address3+" added status : "+a.add(address3));
					System.out.println(address4+" added status : "+a.add(address4));
					
				}
			};
			
			Thread t2=new Thread(){
				@Override
				public void run(){
					System.out.println("Top popped element:"+a.take());
				}
			};
			
			Thread t3=new Thread(){
				@Override
				public void run(){
					System.out.println("Top element:"+a.peek());
				}
			};
			
			Thread t4=new Thread(){
				@Override
				public void run(){
					System.out.println(address3+" removed status :"+a.remove(address3));
					System.out.println(address7+" removed status :"+a.remove(address7));
				}
			};
			
			Thread t5=new Thread(){
				@Override
				public void run(){
					System.out.println(address5+" added status : "+a.add(address5));
					System.out.println(address6+" added status : "+a.add(address6));
					System.out.println(address7+" added status : "+a.add(address7));
				}
			};
			
			t1.start();
			t2.start();
			t3.start();
			t5.start();
			t4.start();

		}catch(Exception e){
			System.err.println("Exception in Address main:"+e.getMessage());
		}
		

}
}