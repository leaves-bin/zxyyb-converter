package com.zxyb.zhconverter;

import java.util.ResourceBundle;

/**
 * 字库基于原项目https://code.google.com/archive/p/java-zhconverter/, 据项目描述来源于MediaWiki.
 * <p>转换规则很简单, 完全不进行分词.
 * <p>如果输入文本不是单字, 如果在对应表中有完全匹配, 就返回对应的文本; 不然就逐字按照单字转换.
 */
public class ZhConverter {

  public enum Target {
   BIG5, ZH
  }
  private final static ZhConverter zhConver = new ZhConverter();
  private final static ZhConverter big5Conver = new ZhConverter();

  private ResourceBundle rb = null;
  
  public static ZhConverter init(Target tar) {
    if (tar.equals(tar.BIG5)) {
    	big5Conver.rb = ResourceBundle.getBundle("zh2bg");
      return big5Conver;
    } else {
    	zhConver.rb = ResourceBundle.getBundle("bg2zh");
      return zhConver;
    }
  }
  
  private ZhConverter() { }
  
  /**
   * 不需自行创建转换器即可转换. 内部调用{@link #转换(String) 转换}方法.
   * @param 文本 任意长度
   * @param 简繁 目标格式
   * @return 转换为目标格式的文本
   * @throws IllegalArgumentException 文本为null时
   */
  public static String convert(String chars, Target tar) {
    return init(tar).convert(chars);
  }

  /**
   * 不进行分词. 如果长度大于1, 寻找匹配的短语. 如没有, 按字寻找对应字后组合.
   * @param 输入文本 任意长度
   * @return 转换后的文本
   * @throws IllegalArgumentException 文本为null时
   */
  public String convert(String chars) {
    if (chars == null) {
      throw new IllegalArgumentException("字符串为null");
    }

    StringBuilder sb = new StringBuilder();

    if (chars.length() > 1 && rb.containsKey(chars)) {
      return rb.getString(chars);
    }

    for (char c : chars.toCharArray()){
      String _c = String.valueOf(c);

      // 如有多个对应字符, 暂时用第一个; 如果没有对应字符, 保留原字符
      sb.append(rb.containsKey(_c) ? rb.getString(_c).charAt(0) : _c);
    }
    return sb.toString();
  }
}
