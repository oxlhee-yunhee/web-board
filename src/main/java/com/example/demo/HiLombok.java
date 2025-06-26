package com.example.demo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HiLombok {
	private final String hello;
	private final int lombok;
	
	public static void main(String[] args)  {
		HiLombok hilombok=new HiLombok("헬로",5);
		System.out.println(hilombok.getHello());
		System.out.println(hilombok.getLombok());
	}
}
