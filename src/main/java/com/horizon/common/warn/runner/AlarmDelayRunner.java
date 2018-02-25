package com.horizon.common.warn.runner;

import org.apache.log4j.Logger;

import com.horizon.common.mq.model.AlarmInfo;
import com.horizon.common.mq.model.IndexValueVO;


/**
 * 超时数据处理任务类
 * @author Administrator
 *
 */
public class AlarmDelayRunner implements Runnable  {

	/**
	 *
	 */
	private static final long serialVersionUID = 8849434942882466073L;

	private static final Logger LOG = Logger.getLogger(AlarmDelayRunner.class);

	private String  chargingPileNum;
	private IndexValueVO indexValueVO;
	

	public AlarmDelayRunner(String chargingPileNum, IndexValueVO indexValueVO) {
		super();
		this.chargingPileNum = chargingPileNum;
		this.indexValueVO = indexValueVO;
	}

	@Override
	public void run() {
//		LOG.info(String.format("桩【%s】故障【%s】发生超过25s-----------望天，这个桩坏了，快来修啊 ", chargingPileNum,indexValueVO.getIndexNum()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chargingPileNum == null) ? 0 : chargingPileNum.hashCode());
		result = prime * result
				+ ((indexValueVO == null) ? 0 : indexValueVO.getIndexNum());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlarmDelayRunner other = (AlarmDelayRunner) obj;
		if (chargingPileNum == null) {
			if (other.chargingPileNum != null)
				return false;
		} else if (!chargingPileNum.equals(other.chargingPileNum))
			return false;
		if (indexValueVO == null) {
			if (other.indexValueVO != null)
				return false;
		} else if (!indexValueVO.equals(other.indexValueVO))
			return false;
		return true;
	}


    

}
