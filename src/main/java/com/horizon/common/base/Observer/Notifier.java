package com.horizon.common.base.Observer;

/**
 * 通知者的 抽象类
 * 
 * @author liy
 * 
 */
public abstract class Notifier {
	private EventHandler eventHandler = new EventHandler();

	public EventHandler getEventHandler() {
		return eventHandler;
	}

	public void setEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	/**
	 * 监听事件
	 * 
	 * @param object
	 *            要执行方法的对象
	 * @param methodName
	 *            执行方法 的方法名
	 * @param args
	 *            执行方法的参数
	 */
	public abstract void addListener(Object object, String methodName,
			Object... args);

	/**
	 * 执行通知
	 */
	public abstract void notifyX();
}