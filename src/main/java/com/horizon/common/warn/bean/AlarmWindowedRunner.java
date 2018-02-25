package com.horizon.common.warn.bean;

import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;

import com.horizon.common.warn.slidwindown.WindowedRunable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlarmWindowedRunner extends WindowedRunable<String>{

	/**
	 *
	 */
	private static final long serialVersionUID = 8849434942882466073L;

	private static final Logger LOG = Logger.getLogger(AlarmWindowedRunner.class);


	public AlarmWindowedRunner(int windowLenInSecs, int emitFrequency){
		super(windowLenInSecs,emitFrequency);
	}

	@Override
	public void emitNormal(String tuple) {
		cache.add(tuple);
	}


	@Override
	public void emitCurrentWindowCounts(){
		int sum = 0;
		List<String> windowedTuples = cache.getAndAdvanceWindow();
		Map<String,Integer> msi = new HashMap<String,Integer>();
		if(windowedTuples!=null && windowedTuples.size()!=0){
			for (String string : windowedTuples) {
				Integer ite = msi.get(string);
				if(ite==null){
					msi.put(string, 1);
				}else{
					msi.put(string, ite+1);
				}
			}
			for (String string : msi.keySet()) {
				if(msi.get(string)>=4){
					String s = "闪告，闪告，闪告，闪告，闪告"+msi.get(string);
					LOG.info("array to sum up【"+string+"】  :" + s);
				}
			}
//			LOG.info("array to sum up size:  " + windowedTuples.size());
//			LOG.info("array to sum up:  " + windowedTuples.toString());
		}
	
	}


}
