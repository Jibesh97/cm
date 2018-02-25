package com.horizon.common.base.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件的 处理者
 * 
 * @author liy
 * 
 */
public class EventHandler {
	// 是用一个List
	private List<Event> objects;

	public EventHandler() {
		objects = new ArrayList<Event>();
	}

	// 添加某个对象要执行的事件，及需要的参数
	public void addEvent(Object object, String methodName, Object... args) {
		objects.add(new Event(object, methodName, args));
	}

	// 通知所有的对象执行指定的事件
	public void notifyX() throws Exception {
		for (Event e : objects) {
			e.invoke();
		}
	}
}
