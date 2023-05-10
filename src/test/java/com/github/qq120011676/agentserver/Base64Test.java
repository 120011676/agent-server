package com.github.qq120011676.agentserver;

import cn.hutool.core.codec.Base64;

public class Base64Test {
    public static void main(String[] args) {
        String url = "https://www.baidu.com/s?wd=%E9%AB%98%E7%AB%AF%E8%A3%85%E5%A4%87%E5%88%B6%E9%80%A0%E9%A2%86%E5%9F%9F%E9%83%BD%E7%BB%8F%E5%8E%86%E4%BA%86%E5%93%AA%E4%BA%9B%EF%BC%9F&sa=fyb_n_homepage&rsv_dl=fyb_n_homepage&from=super&cl=3&tn=baidutop10&fr=top1000&rsv_idx=2&hisfilter=1";
        String a = Base64.encodeUrlSafe(url);
        System.out.println(a);
        String b = Base64.decodeStr(a);
        System.out.println(b);
        a = Base64.encode(url);
        System.out.println(a);
        b = Base64.decodeStr(a);
        System.out.println(b);
    }
}
