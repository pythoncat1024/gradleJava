package com.intellij.cat.proxy.staticProxy;

/**
 * 我是真实明星的代理（冒牌明星）
 */
public class ProxyStar implements Star {

    private Star realStar;

    public ProxyStar(Star realStar) {
        this.realStar = realStar;
    }

    @Override
    public String sing(String... song) {
        bookTicket();
        System.out.println("我是代理啦，我自己是不会唱歌的，我让 Tony 过来唱");
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sing = realStar.sing(song);
        collectMoney();
        return sing;
    }

    /**
     * 订机票的事情当然不是明星亲自做了
     */
    private void bookTicket() {
        System.out.println("帮 tony 订机票...");
    }

    /**
     * 收钱也不是
     */
    private void collectMoney() {
        System.out.println("Tony 这次演唱会开得很成功，我帮 tony 收了 1000 万块钱。");
    }
}
