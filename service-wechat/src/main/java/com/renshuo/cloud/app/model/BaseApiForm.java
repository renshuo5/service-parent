package com.renshuo.cloud.app.model;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * api 基础form
 * 
 * 任硕根据jfinal修改
 */
public class BaseApiForm {

	protected Logger log = LoggerFactory.getLogger(getClass());

	private HttpServletRequest request;
//	private HttpServletResponse response;
	private String basePath;
	//放到p中
//	private Integer pageNo; // 页数
//	private Integer pageSize; // 页码
	private String method; // 方法名
	private String version; // 版本
	private String apiUser; // 调用用户

	private String ip;

	//存放request所有值的map
	private Map<String,Object> map = new HashMap<>();

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	//private String checkSum; // 校验和
//	private String signature; // 签名
//	private String os;
	private String p; // 参数
//	//附件内容
//	private List<UploadFile> uploadFileList;
//
//	public List<UploadFile> getUploadFileList() {
//		return uploadFileList;
//	}
//
//	public void setUploadFileList(List<UploadFile> uploadFileList) {
//		this.uploadFileList = uploadFileList;
//	}

	/**
	 * 获取分页信息
	 * 
	 * 2016年10月3日 下午9:03:02 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
//	public Paginator getPaginator() {
//		Paginator paginator = new Paginator();
//		if (this.pageNo != null && this.pageNo.intValue() > 0)
//			paginator.setPageNo(this.pageNo.intValue());
//		if (this.pageSize != null && this.pageSize.intValue() > 0)
//			paginator.setPageSize(this.pageSize.intValue());
//		return paginator;
//	}

	public BaseApiForm(HttpServletRequest req) {
		this.request= req;
		Enumeration<String> ele = req.getParameterNames();
		while(ele.hasMoreElements()){
			String paraName = ele.nextElement();
			Object val = getRequest().getParameter(paraName);
			this.map.put(paraName,val);
		}
	}

	public BaseApiForm(HttpServletRequest req, HttpServletResponse res) {
		this.request= req;
//		this.response = res;
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public double getDouble(String key) {
		return Double.parseDouble(get(key));
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public long getLong(String key) {
		return Long.parseLong(get(key));
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public int getInt(String key) {
		return Integer.parseInt(get(key));
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		JSONObject json = getParams();
		if (json != null&& json.size()>0) {
			String value = json.getString(key);
			if (StringUtils.isNotBlank(value)) {
				return value;
			} else {
				return StringUtils.isBlank(getRequest().getParameter(key))? "":getRequest().getParameter(key);
			}
		} else {
			return getRequest().getParameter(key);
		}
	}


	public Map<String,Object> getParameters(){
		Enumeration<String> ele = getRequest().getParameterNames();
		Map<String,Object> map = new HashMap<>();
		while(ele.hasMoreElements()){
			String paraName = ele.nextElement();
			Object val = getRequest().getParameter(paraName);
			map.put(paraName,val);
		}
		return map;
	}
	/**
	 * 设置参数
	 * 
	 * @param key
	 * @return
	 */
	public void set(String key,Object value) {
		JSONObject json = getParams();
		if (json != null) {
			json.put(key, value);
		} 
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public JSONObject getJSONObject(String key) {
		return getParams().getJSONObject(key);
	}

	/**
	 * 获取参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @param key
	 * @return
	 */
	public JSONArray getJSONArray(String key) {
		return getParams().getJSONArray(key);
	}

	/**
	 * 获取P参数
	 * 
	 * 2016年10月3日 下午9:02:45 flyfox 369191470@qq.com
	 * 
	 * @return
	 */
	private JSONObject getParams() {
		try {
			if(json==null){
				json = JSON.parseObject(p);
				if(json==null) json = new JSONObject();
			}
		} catch (Exception e) {
			log.error("apiform json parse fail:" + p);
			return new JSONObject();
		}

		return json;
	}
	/**
	 * 此参数可以方法之间共享json
	 */
	private JSONObject json = null;



	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getApiUser() {
		return apiUser;
	}

	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

//	public HttpServletResponse getResponse() {
//		return response;
//	}
//
//	public void setResponse(HttpServletResponse response) {
//		this.response = response;
//	}

  /**
   * @return the basePath
   */
  public String getBasePath() {
    return this.basePath;
  }

  /**
   * @param basePath the basePath to set
   */
  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }

  @Override
	public String toString() {
		return "[page=" + get("pageNo") + "/" + get("pageSize") + "]" //
				+ "[method=" + this.method + "]" //
				+ "[version=" + this.version + "]" //
				+ "[apiUser=" + this.apiUser + "]" //
				+ "[signature=" + get("signature") + "]" //
				+ "[p=" + this.p + "]";
	}

}
