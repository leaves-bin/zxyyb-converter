package com.zxyb;

import com.zxyb.zhconverter.ZhConverter;

public class MainTest {

	public static void main(String[] args) {
		ZhConverter converter = ZhConverter.init(ZhConverter.Target.ZH);
		String simplifiedStr = converter.convert("興奮生意");
		System.out.println(simplifiedStr);
	}
}
