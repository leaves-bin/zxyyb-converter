package com.zxyb;

import com.zxyb.zhconverter.ZHConverter;

public class MainTest {

	public static void main(String[] args) {
		ZHConverter converter = ZHConverter.getInstance(ZHConverter.ZH);
		String simplifiedStr = converter.convert("興奮生意");
		System.out.println(simplifiedStr);
	}
}
