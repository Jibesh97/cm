package com.horizon.common.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.horizon.common.base.Config;


public class ReqEngine {
	
	private static Logger log = Logger.getLogger(ReqEngine.class);
	/**
	 * @author TAOSH
	 * 请求 选择文件接口
	 * @param hdfsURL
	 * @param urlPrefix
	 * @return
	 * @throws IOException
	 */
	public static String importFile(String hdfsURL,String urlPrefix) throws IOException{
		String reqUrl = urlPrefix + "/3/ImportFiles?path="+hdfsURL;
		return requestRoadJson(reqUrl, null);
	}
	/**
	 * @author taosh
	 * @param urlPrefix
	 * @param params source_frames:"["hdfs://PC:9000/hdRoot/41030402427.csv"]"
	 * @return
	 * @throws IOException
	 */
	public static String parseSetup(String hdfsURL,String urlPrefix) throws IOException{
		log.debug("parseSetup params hdfsURL:"+hdfsURL);
		String decode = URLEncoder.encode("[\""+hdfsURL+"\"]", "UTF-8");
		String reqUrl = urlPrefix + "/3/ParseSetup?source_frames="+decode;
		log.debug("parseSetup params:"+reqUrl);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("source_frames", decode);
		//params.put("source_frames", "%5B%22hdfs%3A%2F%2FPC%3A9000%2FhdRoot%2F41030402427.csv%22%5D");
		return requestRoadJson(reqUrl, null);
	}
//	/**
//	 * @author taosh
//	 * @param urlPrefix
//	 * @param DeepLearningParse
//	 * @return
//	 * @throws IOException
//	 */
//	public static String parse(String urlPrefix,DeepLearningParse vo) throws IOException{
//		String params = SystemUtil.generateVO2String(vo);
//		log.debug("parse params:"+params);
//		String reqUrl = urlPrefix + "/3/Parse?"+ params;
//	//	"destination_frame=X410304024274.hex&source_frames=%5B%22hdfs%3A%2F%2FPC%3A9000%2FhdRoot%2F41030402427.csv%22%5D&parse_type=CSV&separator=44&number_columns=21&single_quotes=false&column_names=%5B%22Instance_number%22%2C%22driver%22%2C%22trip%22%2C%22Average_speed%22%2C%22Average_ABS_Acceleration%22%2C%22Average_RPM%22%2C%22Variance_speed%22%2C%22Variance_ABS_Acceleration%22%2C%22Variance_RPM%22%2C%22v_a%22%2C%22v_b%22%2C%22v_c%22%2C%22v_d%22%2C%22a_a%22%2C%22a_b%22%2C%22a_c%22%2C%22r_a%22%2C%22r_b%22%2C%22r_c%22%2C%22Catrgory%22%2C%22DPI%22%5D&column_types=%5B%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Numeric%22%2C%22Enum%22%2C%22Numeric%22%5D&check_header=1&delete_on_done=true&chunk_size=4194304";
//		return requestRoadJson(reqUrl, null);
//	}
//	/**
//	 * @author taosh
//	 * @param urlPrefix
//	 * @param DeepLearningParamsVO
//	 * @return
//	 * @throws IOException
//	 */
//	public static String buildModel(String urlPrefix ,DeepLearningVO vo) throws IOException{
//		String params = SystemUtil.generateVO2String(vo);
//		String reqUrl = urlPrefix + "/3/ModelBuilders/deeplearning?"+params;
//		//"model_id=deeplearning-4fac569d-0bdf-41e6-860d-d484c82e324b&training_frame=X410304024271.hex&nfolds=0&response_column=trip&ignored_columns=&ignore_const_cols=true&activation=Rectifier&hidden=%5B200%2C200%5D&epochs=10&variable_importances=false&checkpoint=&use_all_factor_levels=true&train_samples_per_iteration=-2&adaptive_rate=true&input_dropout_ratio=0&l1=0&l2=0&loss=Automatic&distribution=AUTO&score_interval=5&score_training_samples=10000&score_duty_cycle=0.1&stopping_rounds=5&stopping_metric=AUTO&stopping_tolerance=0&replicate_training_data=true&autoencoder=false&overwrite_with_best_model=true&target_ratio_comm_to_comp=0.05&seed=6495133209575747000&rho=0.99&epsilon=1e-8&max_w2=Infinity&initial_weight_distribution=UniformAdaptive&regression_stop=0.000001&diagnostics=true&fast_mode=true&force_load_balance=true&single_node_mode=false&shuffle_training_data=false&missing_values_handling=MeanImputation&quiet_mode=false&sparse=false&col_major=false&average_activation=0&sparsity_beta=0&max_categorical_features=2147483647&reproducible=false&export_weights_and_biases=false&elastic_averaging=false";
//		//Map<String,Object> params = SystemUtil.generateVO2Map(vo);
//		return requestRoadJson(reqUrl, null);
//	}
	
	/**
	 * @author taosh
	 * @param urlPrefix
	 * @param hdfsURL
	 * @return
	 * @throws IOException
	 */
	public static String importModel(String urlPrefix ,String hdfsURL) throws IOException{
		String decode = URLEncoder.encode(hdfsURL, "UTF-8");
		String reqUrl = urlPrefix + "/99/Models.bin/not_in_use?dir="+decode+"&force=false";
		//"dir=hdfs%3A%2F%2F192.168.88.198%3A8020%2Fout%2Fdeeplearning-qdx&force=false";
		//Map<String,Object> params = SystemUtil.generateVO2Map(vo);
		return requestRoadJson(reqUrl, null);
	}
	
	/**
	 * @author taosh
	 * queryModel
	 * @param urlPrefix
	 * @param predictID
	 * @param modelID
	 * @param hexName
	 * @return
	 * @throws IOException
	 */
	public static String queryModel(String urlPrefix ,String modelID) throws IOException{
		String reqUrl = urlPrefix + "/3/Models/"+modelID;
		log.debug("queryModel:"+reqUrl);
		return requestGetJson(reqUrl, null);
	}
	/**
	 * @author taosh
	 * @param urlPrefix
	 * @param predictID
	 * @param modelID
	 * @param hexName
	 * @return
	 * @throws IOException
	 */
	public static String predictModel(String urlPrefix ,String predictID,String modelID,String hexName) throws IOException{
		String reqUrl = urlPrefix + "/3/Predictions/models/"+modelID+"/frames/"+hexName+"?predictions_frame="+predictID;
		log.debug("predictModel:"+reqUrl);
		return requestRoadJson(reqUrl, null);
	}

	public static String exportModel(String serverURL, String hdfsModelURL,String modelID) throws Exception {
		String decode = URLEncoder.encode(hdfsModelURL+modelID, "UTF-8");
		log.info("decodeUrl"+decode);
		String reqUrl = serverURL+"/99/Models.bin/"+modelID+"?dir="+decode+"&force=false";
		log.info("reqUrl"+reqUrl);
		return requestGetJson(reqUrl, null);
	}
	/**
	 * @author taosh
	 * @param Rapids
	 * @param hdfsURL
	 * @return
	 * @throws IOException
	 */
	public static String showPredict(String urlPrefix ,String predictionID) throws IOException{
		String reqUrl = urlPrefix + "/3/Frames/"+predictionID+"?column_offset=0&column_count=20";
		//"ast=(assign+combined-prediction-9d10754b-43f3-4b77-9a80-d90696fea411+(cbind+prediction-9d10754b-43f3-4b77-9a80-d90696fea411+X1450858175581_41030402427qdx.hex))";
		//Map<String,Object> params = SystemUtil.generateVO2Map(vo);
		return requestGetJson(reqUrl, null);
	}
	/**
	 * @author taosh
	 * @param Rapids
	 * @param hdfsURL
	 * @return
	 * @throws IOException
	 */
	public static String rapids(String urlPrefix ,String hexName,String predictID,String combinedID) throws IOException{
		String reqUrl = urlPrefix + "/99/Rapids";
		String ss = "ast=(assign "+combinedID+" (cbind "+predictID+" "+hexName+"))";
		//String ss = "ast=(assign combined-prediction-7d174c96-43b4-44f4-bfbc-c0648761d13d (cbind prediction-7d174c96-43b4-44f4-bfbc-c0648761d13d X1451286918514_41030402427qdx4.hex))";
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("ast", ss);
		return requestContent(reqUrl, ss);
	}
	/**
	 * @author taosh
	 * @param Rapids
	 * @param hdfsURL
	 * @return
	 * @throws IOException
	 */
	public static String combinedPrediction(String urlPrefix ,String combinedPrediction) throws IOException{
		String reqUrl = urlPrefix + "/3/Frames/"+combinedPrediction+"?column_offset=0&column_count=20";
		//"ast=(assign+combined-prediction-9d10754b-43f3-4b77-9a80-d90696fea411+(cbind+prediction-9d10754b-43f3-4b77-9a80-d90696fea411+X1450858175581_41030402427qdx.hex))";
		//Map<String,Object> params = SystemUtil.generateVO2Map(vo);
		return requestGetJson(reqUrl, null);
	}
	
	
	public static String requestRoadJson(String reqUrl, Map<String,Object> params) throws IOException{
		BufferedReader in = null;
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Charset", "utf-8");
			con.setRequestProperty("contentType", "application/x-www-form-urlencoded; charset=UTF-8");
			//设置参数
			if(params != null){
				Set<String> set = params.keySet();
				for(Iterator<String> iter = set.iterator(); iter.hasNext();){
					   String key = iter.next();
					   String value = params.get(key).toString();
					   con.setRequestProperty(key, value);
				}
			}
			
			con.setDoOutput(true);//是否输入参数
			con.setDoInput(true);
			int responseCode = con.getResponseCode();
			log.info("Response code: " + responseCode);
			 in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));  
			 StringBuffer buffer = new StringBuffer();  
			 String line = "";  
			 while ((line = in.readLine()) != null){  
			     buffer.append(line);  
			 }  
			 log.debug(buffer.toString());
			 return buffer.toString();
		
		} catch (Throwable e) {
			log.error(e.getMessage());
			return "0";
		} finally{
			try {
				in.close();
			} catch (Exception e2) {
				log.error(e2.getMessage());			
			}
		}
		}
	
	public static String requestDeleteJson(String reqUrl, Map<String,Object> params) throws IOException{
		BufferedReader in = null;
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("DELETE");
			con.setRequestProperty("Accept-Charset", "utf-8");
			con.setRequestProperty("contentType", "application/x-www-form-urlencoded; charset=UTF-8");
			//设置参数
			if(params != null){
				Set<String> set = params.keySet();
				for(Iterator<String> iter = set.iterator(); iter.hasNext();){
					   String key = iter.next();
					   String value = params.get(key).toString();
					   con.setRequestProperty(key, value);
				}
			}
			
			con.setDoOutput(true);//是否输入参数
			con.setDoInput(true);
			int responseCode = con.getResponseCode();
			log.info("Response code: " + responseCode);
			 in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));  
			 StringBuffer buffer = new StringBuffer();  
			 String line = "";  
			 while ((line = in.readLine()) != null){  
			     buffer.append(line);  
			 }  
			 log.debug(buffer.toString());
			 return buffer.toString();
		
		} catch (Throwable e) {
			log.error(e.getMessage());
			return "0";
		} finally{
			try {
				in.close();
			} catch (Exception e2) {
				log.error(e2.getMessage());
			}
		}
		}
	public static String requestGetJson(String reqUrl, Map<String,Object> params) throws IOException{
		BufferedReader in = null;
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept-Charset", "utf-8");
			con.setRequestProperty("contentType", "application/x-www-form-urlencoded; charset=UTF-8");
			//设置参数
			if(params != null){
				Set<String> set = params.keySet();
				for(Iterator<String> iter = set.iterator(); iter.hasNext();){
					   String key = iter.next();
					   String value = params.get(key).toString();
					   con.setRequestProperty(key, value);
				}
			}
			con.setDoOutput(true);
			con.setDoInput(true);
			int responseCode = con.getResponseCode();
			log.info("Response code: " + responseCode);
			//if (responseCode == 200) {
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));  
			StringBuffer buffer = new StringBuffer();  
			String line = "";  
			while ((line = in.readLine()) != null){  
			    buffer.append(line);  
			}  
			log.debug(buffer.toString());
			return buffer.toString();
			//}
		} catch (Throwable e) {
			log.error(e.getMessage());
			return "0";
		} finally{
			in.close();
		}
		}
	
	public static String requestContent(String reqUrl, String content) throws IOException{
		BufferedReader in = null;
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Accept-Charset", "utf-8");
			con.setRequestProperty("contentType", "application/x-www-form-urlencoded; charset=UTF-8");
			//设置参数
			con.setDoOutput(true);
			con.setDoInput(true);
			OutputStream out = con.getOutputStream();
			out.write(content.getBytes());
			out.close();
			int responseCode = con.getResponseCode();
			log.info("Response code: " + responseCode);
			 in = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));  
			 StringBuffer buffer = new StringBuffer();  
			 String line = "";  
			 while ((line = in.readLine()) != null){  
			     buffer.append(line);  
			 }  
			 log.debug(buffer.toString());
			 return buffer.toString();
		
		} catch (Throwable e) {
			log.error(e.getMessage());
			return "0";
		} finally{
			try {
				in.close();
			} catch (Exception e2) {
				log.error(e2.getMessage());
			}
		}
		}
	
	public static String sendPostReq(String reqURL, String reqBody) {
		String result = null;
		BufferedReader in = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(reqURL).openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			OutputStream out = conn.getOutputStream();
			if(StringUtils.isNotBlank(reqBody)){
				out.write(reqBody.getBytes());	
			}
			int code = conn.getResponseCode();
			log.info(new Integer(code).toString());
			if (code >= 200 && code < 300) {
				result =  IoUtility.readStringFromInputStream(conn.getInputStream(), "utf-8");
			}else{
				result =  IoUtility.readStringFromInputStream(conn.getErrorStream(), "utf-8");
			}
			out.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
				}
		}
		// 返回
		return result;
	}
	
	/**
	 * @author taos
	 * 删除context url
	 * @param contextName
	 * @return
	 */
	public static String generateKillContextURL(String contextName) {
		// TODO Auto-generated method stub
		String extIP = Config.props.getProperty("jobServer.extip");
		String extPort = Config.props.getProperty("jobServer.extport");
		//DELETE /contexts/<name>     - stops a context and all jobs running in it
		StringBuilder sb = new StringBuilder(extIP);
		sb.append(":");
		sb.append(extPort);
		sb.append("/contexts/");
		sb.append(contextName);
		log.info(sb.toString());
		return sb.toString();
	}
	
	/**
	 * @author 
	 * put 方式请求rest 接口
	 * @param reqBody
	 * @return
	 */
	public static String sendDelReq(String reqURL, String reqBody) {
		String result = null;
		BufferedReader in = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(reqURL).openConnection();
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Accept-Charset", "utf-8");
			conn.setRequestProperty("contentType", "utf-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			OutputStream out = conn.getOutputStream();
			if(StringUtils.isNotBlank(reqBody)){
				out.write(reqBody.getBytes());	
			}
			int code = conn.getResponseCode();
			log.info(new Integer(code).toString());
			if (code >= 200 && code < 300) {
				result =  IoUtility.readStringFromInputStream(conn.getInputStream(), "utf-8");
			}else{
				result =  IoUtility.readStringFromInputStream(conn.getErrorStream(), "utf-8");
			}
			out.close();
		} catch (Exception e) {
			log.error(e.getMessage());
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
				}
		}
		// 返回
		return result;
	}
		/**
		 * KILL spark-submit 任务
		 * @author zhangx
		 * @param urlStr http://192.168.88.10:8088/ws/v1/cluster/apps/application_1489629879933_0018/state
		 * @param reqBody {"state":"KILLED"}
		 * @return
		 * @throws Exception
		 * @since JDK 1.6
		 */
	   public static String doPut(String urlStr,String reqBody) throws Exception{
			String result = null;
			BufferedReader in = null;
			try {
				HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
				conn.setRequestMethod("PUT");
				conn.setRequestProperty("Accept-Charset", "utf-8");
				conn.setRequestProperty("Content-Type", "application/json");
				conn.setDoOutput(true);
				conn.setDoInput(true);
				OutputStream out = conn.getOutputStream();
				if(StringUtils.isNotBlank(reqBody)){
					out.write(reqBody.getBytes());	
				}
				int code = conn.getResponseCode();
				log.info(new Integer(code).toString());
				if (code >= 200 && code < 300) {
					result =  IoUtility.readStringFromInputStream(conn.getInputStream(), "utf-8");
				}else{
					result =  IoUtility.readStringFromInputStream(conn.getErrorStream(), "utf-8");
				}
				out.close();
			} catch (Exception e) {
				log.error(e.getMessage());
			} finally {
				if (in != null)
					try {
						in.close();
					} catch (IOException e) {
						log.error(e.getMessage());
					}
			}
			// 返回
			return result;
	                
	     }
	    
}
