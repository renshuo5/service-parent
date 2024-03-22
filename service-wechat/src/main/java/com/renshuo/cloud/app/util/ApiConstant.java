package com.renshuo.cloud.app.util;

import java.io.File;

public class ApiConstant {

	/**
	 * 成功
	 */
	public static final int CODE_SUCCESS = 200;
	/**
	 * 失败
	 */
	public static final int CODE_FAIL = -1;
	/**
	 * 维护
	 */
	public static final int CODE_SERVER_MAINTAIN = -9;
	/**
	 * 版本号错误
	 */
	public static final int CODE_VERSION_ERROR = -101;
	/**
	 * 调用方法不存在
	 */
	public static final int CODE_METHOD_ERROR = -103;
	/**
	 * 调用方法异常
	 */
	public static final int CODE_METHOD_HANDLER_ERROR = -104;
	/**
	 * 传递参数异常
	 */
	public static final int CODE_PARAM_ERROR = -102;
	/**
	 * IP黑名单
	 */
	public static final int CODE_IP_BLACK = -201;

	/**
	 * 登陆验证异常
	 */
	public static final int CODE_LOGIN_VALID_ERROR = -501;

	/**
	 * token不存在
	 */
	public static final int CODE_TOKEN_VALID_ERROR = -901;
	
	/**
	 * 验证签名异常
	 */
	public static final int CODE_SIGN_VALID_ERROR = -902;

	public static final String MSG_SUCCESS = "success";
	public static final String MSG_FAIL = "fail";
	public static final String MSG_VERSION_ERROR = "版本号错误";
	public static final String MSG_METHOD_ERROR = "调用方法不存在";
	public static final String MSG_METHOD_HANDLER_ERROR = "调用方法异常";
	public static final String MSG_PARAM_ERROR = "传递参数异常";
	public static final String MSG_IP_BLACK = "IP黑名单拦截";
	public static final String MSG_SERVER_MAINTAIN = "API服务维护中";
	public static final String MSG_LOGIN_VALID_ERROR = "登陆验证失败";
	public static final String MSG_TOKEN_VALID_ERROR = "验证TOKEN失败";
	public static final String MSG_SIGN_VALID_ERROR = "验证签名失败";
	
	
	/*-----------------------------手机app------------------------------*/
	/**登录接口名称*/
	public static final String APP_LOGIN_METHOD_NAME = "login";
	/** timestamp参数名称 */
	public static final String TIMESTAMP_PARAMNAME = "timestamp";
	/** 业务接口token参数名称 */
	public static final String BIZ_TOKEN_NAME = "sessionid";
	/** devicetoken参数名称 */
	public static final String DEVICETOKEN_NAME = "devicetoken";
	/** request.setAttribute token对象属性名称 */
	public static final String REQUEST_ATTR_TOKEN = "TOKEN_VALUE";
	/** request.setAttribute account对象属性名称 */
	public static final String REQUEST_ATTR_ACCOUNT = "ACCOUNT_NAME";
	/** 登录错误次数 */
	public static final String LOGIN_ERROR_COUNT = "LOGIN_ERROR_COUNT";
	/** 验证码参数名 */
	public static final String IMG_CODE_KEY = "imgcodekey";
	/** 签名参数名称 */
	public static final String SIGNATURE_PARAMNAME = "signature";
	/** 不参与签名的参数名称 */
	public static final String EXCLUDE_PARAMNAMES[] = new String[] { "method", "signature" };
	
	/*-----------------------------缓存相关------------------------------*/
	/** 验证码缓存名称 */
	public static final String CACHE_IMG_KEYCODE_NAME = "imgkeycodeCache";
	/** 登录错误次数缓存名称 */
	public static final String CACHE_LOGIN_ERROR_COUNT_NAME = "loginErrorCountCache";
	
	public static final int UPLOAD_MAX = 10 * 1024 * 1024;
	
//	/**
//	 * 基础目录
//	 */
//	public static final String BASE_PATH = ApiUtil.getWebRootPath() + File.separator;
//	/**
//	 * tomcat目录
//	 */
//	public static final String TOMCAT_WEBAPPS_PATH = ApiUtil.getTomcatWebAppsPath() + File.separator;

	/**
	 * 基础JFLYFOX目录
	 */
	public static final String MEIS_PATH = "meis_upload" + File.separator;
	
}
