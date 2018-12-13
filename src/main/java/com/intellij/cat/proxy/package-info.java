/**
 * 代理模式<br/>
 * <p>背景介绍</p>
 * 比如有人请歌星唱歌，对于歌星本人而言，只需要做一件事情，就是唱。
 * 但是对于其工作室，需要先帮忙订机票，然后让歌星去唱歌，之后，工作室还有做一些收尾的工作，比如，接广告，接采访安排等
 * <p>
 * 代理模式的套路是，代理类与实际类实现共同的抽象角色，然后对外，一致是抽象角色。然后内部分工。
 * <p>
 * 比如除了唱歌之外的事情，是代理搞定，唱歌是本人搞定。
 * <p>
 * <hr/>
 * <b>代理模式中一共有4个角色：<b/>
 * <ol>
 * <li>客户角色。作为调用者出现的（对应这里的是 {@link com.intellij.cat.proxy.dynamicProxy.Client}）</li>
 * <li>抽象业务角色，作为对接客户的角色出现的 (对应这里的是 {@link com.intellij.cat.proxy.dynamicProxy.Star})</li>
 * <li>真实业务角色，作为核心逻辑的真实角色出现的 （对应这里的是 {@link com.intellij.cat.proxy.dynamicProxy.RealStar}）</li>
 * <li>代理业务角色，如果是动态代理，没有一个具体的类（jdk 自动生成代理类）；如果是静态代理，有对应的类（对应这里的是 {@link com.intellij.cat.proxy.staticProxy.ProxyStar}）</li>
 * </ol>
 * <p>
 * <hr/>
 *
 * <b>何谓动态代理：即动态生成代理类！</b>
 */
package com.intellij.cat.proxy;