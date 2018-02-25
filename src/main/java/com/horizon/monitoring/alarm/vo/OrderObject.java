package com.horizon.monitoring.alarm.vo;

import java.util.Date;
/**
 * 和接口OrderObject对应的vo
 * @author liw
 *
 */
public class OrderObject {

	private String order_id;
	private String charge_station_id;
	private String charge_station;
	private int patrol_id;
	private Date start_date;
	private int patrol_person_id;
	private String patrol_person;
	private int distribute_person_id;
	private String distribute_person;
	private int patrol_team_id;
	private String patrol_team;
	private String patrol_reason;
	private String alarm_type;
	private String status;
	private int fault_type;
	private String duration;
	private int task_channel;
	private Date complete_time;
	private int get_single_person_id;
	private String get_single_person;
	private Date get_single_time;
	private String operate;
	private int is_alert;
	private String alert_content;
	private int is_warn;
	private String warn_content;
	private String tid;
	private int business_type = 0;
	private int curr_status = 0;
	private int curr_client_status = 0;
	private int source = 0;
	private int order_type = 0;
	private int pile_id;
	private String equip_run_num;
	private String wait_receive_person;
	private int wait_receive_person_id;
	private int scan_code_time;
	private String patrol_images;
	private String patrol_feedback;
	private String defect_id;
	private int is_defect = 0;
	private int is_done;
	private String sub_tid;
	private int proc_code;
	private int order_code;
	private int taskchannel_code;
	private int pending_code;
	private int tourstatus_code;
	private int platformtask_code;
	private int receive_persion_id;
	private String receive_persion;
	private Date receive_time;
	private int is_arrive_site;
	private Date arrived_time;
	private String send_emergency_workers;
	private int send_emergency_workers_id;
	private String rush_repair_records;
	private Date distribute_time;
	private int is_finished;
	private int finished_id;
	private int feedback_id;
	private Date finished_time;
	private String remarks;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getCharge_station_id() {
		return charge_station_id;
	}

	public void setCharge_station_id(String charge_station_id) {
		this.charge_station_id = charge_station_id;
	}

	public String getCharge_station() {
		return charge_station;
	}

	public void setCharge_station(String charge_station) {
		this.charge_station = charge_station;
	}

	public int getPatrol_id() {
		return patrol_id;
	}

	public void setPatrol_id(int patrol_id) {
		this.patrol_id = patrol_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public int getPatrol_person_id() {
		return patrol_person_id;
	}

	public void setPatrol_person_id(int patrol_person_id) {
		this.patrol_person_id = patrol_person_id;
	}

	public String getPatrol_person() {
		return patrol_person;
	}

	public void setPatrol_person(String patrol_person) {
		this.patrol_person = patrol_person;
	}

	public int getDistribute_person_id() {
		return distribute_person_id;
	}

	public void setDistribute_person_id(int distribute_person_id) {
		this.distribute_person_id = distribute_person_id;
	}

	public String getDistribute_person() {
		return distribute_person;
	}

	public void setDistribute_person(String distribute_person) {
		this.distribute_person = distribute_person;
	}

	public int getPatrol_team_id() {
		return patrol_team_id;
	}

	public void setPatrol_team_id(int patrol_team_id) {
		this.patrol_team_id = patrol_team_id;
	}

	public String getPatrol_team() {
		return patrol_team;
	}

	public void setPatrol_team(String patrol_team) {
		this.patrol_team = patrol_team;
	}

	public String getPatrol_reason() {
		return patrol_reason;
	}

	public void setPatrol_reason(String patrol_reason) {
		this.patrol_reason = patrol_reason;
	}

	public String getAlarm_type() {
		return alarm_type;
	}

	public void setAlarm_type(String alarm_type) {
		this.alarm_type = alarm_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFault_type() {
		return fault_type;
	}

	public void setFault_type(int fault_type) {
		this.fault_type = fault_type;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getTask_channel() {
		return task_channel;
	}

	public void setTask_channel(int task_channel) {
		this.task_channel = task_channel;
	}

	public Date getComplete_time() {
		return complete_time;
	}

	public void setComplete_time(Date complete_time) {
		this.complete_time = complete_time;
	}

	public int getGet_single_person_id() {
		return get_single_person_id;
	}

	public void setGet_single_person_id(int get_single_person_id) {
		this.get_single_person_id = get_single_person_id;
	}

	public String getGet_single_person() {
		return get_single_person;
	}

	public void setGet_single_person(String get_single_person) {
		this.get_single_person = get_single_person;
	}

	public Date getGet_single_time() {
		return get_single_time;
	}

	public void setGet_single_time(Date get_single_time) {
		this.get_single_time = get_single_time;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public int getIs_alert() {
		return is_alert;
	}

	public void setIs_alert(int is_alert) {
		this.is_alert = is_alert;
	}

	public String getAlert_content() {
		return alert_content;
	}

	public void setAlert_content(String alert_content) {
		this.alert_content = alert_content;
	}

	public int getIs_warn() {
		return is_warn;
	}

	public void setIs_warn(int is_warn) {
		this.is_warn = is_warn;
	}

	public String getWarn_content() {
		return warn_content;
	}

	public void setWarn_content(String warn_content) {
		this.warn_content = warn_content;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public int getBusiness_type() {
		return business_type;
	}

	public void setBusiness_type(int business_type) {
		this.business_type = business_type;
	}

	public int getCurr_status() {
		return curr_status;
	}

	public void setCurr_status(int curr_status) {
		this.curr_status = curr_status;
	}

	public int getCurr_client_status() {
		return curr_client_status;
	}

	public void setCurr_client_status(int curr_client_status) {
		this.curr_client_status = curr_client_status;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getOrder_type() {
		return order_type;
	}

	public void setOrder_type(int order_type) {
		this.order_type = order_type;
	}

	public int getPile_id() {
		return pile_id;
	}

	public void setPile_id(int pile_id) {
		this.pile_id = pile_id;
	}

	public String getEquip_run_num() {
		return equip_run_num;
	}

	public void setEquip_run_num(String equip_run_num) {
		this.equip_run_num = equip_run_num;
	}

	public String getWait_receive_person() {
		return wait_receive_person;
	}

	public void setWait_receive_person(String wait_receive_person) {
		this.wait_receive_person = wait_receive_person;
	}

	public int getWait_receive_person_id() {
		return wait_receive_person_id;
	}

	public void setWait_receive_person_id(int wait_receive_person_id) {
		this.wait_receive_person_id = wait_receive_person_id;
	}

	public int getScan_code_time() {
		return scan_code_time;
	}

	public void setScan_code_time(int scan_code_time) {
		this.scan_code_time = scan_code_time;
	}

	public String getPatrol_images() {
		return patrol_images;
	}

	public void setPatrol_images(String patrol_images) {
		this.patrol_images = patrol_images;
	}

	public String getPatrol_feedback() {
		return patrol_feedback;
	}

	public void setPatrol_feedback(String patrol_feedback) {
		this.patrol_feedback = patrol_feedback;
	}

	public String getDefect_id() {
		return defect_id;
	}

	public void setDefect_id(String defect_id) {
		this.defect_id = defect_id;
	}

	public int getIs_defect() {
		return is_defect;
	}

	public void setIs_defect(int is_defect) {
		this.is_defect = is_defect;
	}

	public int getIs_done() {
		return is_done;
	}

	public void setIs_done(int is_done) {
		this.is_done = is_done;
	}

	public String getSub_tid() {
		return sub_tid;
	}

	public void setSub_tid(String sub_tid) {
		this.sub_tid = sub_tid;
	}

	public int getProc_code() {
		return proc_code;
	}

	public void setProc_code(int proc_code) {
		this.proc_code = proc_code;
	}

	public int getOrder_code() {
		return order_code;
	}

	public void setOrder_code(int order_code) {
		this.order_code = order_code;
	}

	public int getTaskchannel_code() {
		return taskchannel_code;
	}

	public void setTaskchannel_code(int taskchannel_code) {
		this.taskchannel_code = taskchannel_code;
	}

	public int getPending_code() {
		return pending_code;
	}

	public void setPending_code(int pending_code) {
		this.pending_code = pending_code;
	}

	public int getTourstatus_code() {
		return tourstatus_code;
	}

	public void setTourstatus_code(int tourstatus_code) {
		this.tourstatus_code = tourstatus_code;
	}

	public int getPlatformtask_code() {
		return platformtask_code;
	}

	public void setPlatformtask_code(int platformtask_code) {
		this.platformtask_code = platformtask_code;
	}

	public int getReceive_persion_id() {
		return receive_persion_id;
	}

	public void setReceive_persion_id(int receive_persion_id) {
		this.receive_persion_id = receive_persion_id;
	}

	public String getReceive_persion() {
		return receive_persion;
	}

	public void setReceive_persion(String receive_persion) {
		this.receive_persion = receive_persion;
	}

	public Date getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(Date receive_time) {
		this.receive_time = receive_time;
	}

	public int getIs_arrive_site() {
		return is_arrive_site;
	}

	public void setIs_arrive_site(int is_arrive_site) {
		this.is_arrive_site = is_arrive_site;
	}

	public Date getArrived_time() {
		return arrived_time;
	}

	public void setArrived_time(Date arrived_time) {
		this.arrived_time = arrived_time;
	}

	public String getSend_emergency_workers() {
		return send_emergency_workers;
	}

	public void setSend_emergency_workers(String send_emergency_workers) {
		this.send_emergency_workers = send_emergency_workers;
	}

	public int getSend_emergency_workers_id() {
		return send_emergency_workers_id;
	}

	public void setSend_emergency_workers_id(int send_emergency_workers_id) {
		this.send_emergency_workers_id = send_emergency_workers_id;
	}

	public String getRush_repair_records() {
		return rush_repair_records;
	}

	public void setRush_repair_records(String rush_repair_records) {
		this.rush_repair_records = rush_repair_records;
	}

	public Date getDistribute_time() {
		return distribute_time;
	}

	public void setDistribute_time(Date distribute_time) {
		this.distribute_time = distribute_time;
	}

	public int getIs_finished() {
		return is_finished;
	}

	public void setIs_finished(int is_finished) {
		this.is_finished = is_finished;
	}

	public int getFinished_id() {
		return finished_id;
	}

	public void setFinished_id(int finished_id) {
		this.finished_id = finished_id;
	}

	public int getFeedback_id() {
		return feedback_id;
	}

	public void setFeedback_id(int feedback_id) {
		this.feedback_id = feedback_id;
	}

	public Date getFinished_time() {
		return finished_time;
	}

	public void setFinished_time(Date finished_time) {
		this.finished_time = finished_time;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
