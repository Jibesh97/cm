package com.horizon.common.warn.slidwindown;

import org.apache.log4j.Logger;

/**
 * 窗口操作，窗口初始化 数据写入，读取
 * 
 * @author Administrator
 *
 * @param <T>
 */
public abstract class WindowedRunable<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8849434942882466073L;

	private static final Logger LOG = Logger.getLogger(WindowedRunable.class);

	private final static int DEFAULT_WINDOW_LEN_IN_SECS = 12;
	private final static int DEFAULT_WINDOW_EMIT_FREQ = 4;

	private int windowLengthInSeconds;
	private int emitFrequencyInSeconds;
	
	protected SlidingWindowCache<T> cache;


	public WindowedRunable(){
		this(DEFAULT_WINDOW_LEN_IN_SECS,DEFAULT_WINDOW_EMIT_FREQ);
	}

	public WindowedRunable(int windowLenInSecs, int emitFrequency){
		if(windowLenInSecs%emitFrequency!=0){
			LOG.warn(String.format("Actual window length(%d) isnot emitFrequency(%d)'s times"));
		}
		this.windowLengthInSeconds = windowLenInSecs;
		this.emitFrequencyInSeconds = emitFrequency;
		cache = new SlidingWindowCache<T>(getSlots(this.windowLengthInSeconds,this.emitFrequencyInSeconds));
	}
	
	private int getSlots(int windowLenInSecs, int emitFrequency){
		return windowLenInSecs/emitFrequency;
	}


	public abstract void emitNormal(T tuple);

	public abstract void emitCurrentWindowCounts();
	


}
