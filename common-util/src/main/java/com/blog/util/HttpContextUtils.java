package com.blog.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.stream.Stream;

public class HttpContextUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpContextUtils.class);

	/**
	 * 获得当前的请求
	 */
	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return servletRequestAttributes == null ? null : servletRequestAttributes.getRequest();
	}


	public static String getChannelFromUserAgent() {
		return getValueFromUserAgent("channel");
	}

	private static String getValueFromUserAgent(String key) {
		HttpServletRequest request = getHttpServletRequest();
		String value = null;
		if (request != null) {
			String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
			if (StringUtils.isNotBlank(userAgent)) {
				//以空格分隔
				String[] parts = userAgent.split(" ");
				String channelPart = Stream.of(parts).filter(s -> s.startsWith(key)).findAny().orElse("");
				if (StringUtils.isNotBlank(channelPart)) {
					value = channelPart.replace(key, "");
					if (StringUtils.isNotBlank(value) && value.length() > 1) {
						value = value.substring(1);
					}
				}
			}
		}
		return value == null ? "" : value.trim();
	}

	private static String getVauleFromHeader(String key) {
		HttpServletRequest request = getHttpServletRequest();
		String value = null;
		if (request != null) {
			value = request.getHeader(key);
		}
		return value == null ? "" : value.trim();
	}

	/**
	 * 获得指定的消息
	 * @param code 消息编码
	 * @param params 消息参数
	 * @return 消息内容
	 */
	public static String getMessage(int code, Object... params) {
		return getMessage(String.valueOf(code), params);
	}

	/**
	 * 获得指定的消息
	 * @param code 消息编码
	 * @param params 消息参数
	 * @return 消息内容
	 */
	public static String getMessage(String code, Object... params) {
		HttpServletRequest request = getHttpServletRequest();
		String content = "";
		if (null != request) {
			//优先从请求参数中获取语言参数
			Locale locale = getLocaleFromRequest(request);
			ApplicationContext applicationContext = RequestContextUtils.findWebApplicationContext(request);
			if (applicationContext != null) {
				try {
					content = applicationContext.getMessage(code, params, locale);
				} catch (Exception e) {
					LOGGER.error("Fail to get the message content for code " + code + " with locale " + locale.getDisplayName(), e);
				}
			}
		} else {
			content = code;
		}
		return content;
	}

	/**
	 * 从请求中获取语言信息
	 * @param request
	 * @return
	 */
	public static Locale getLocaleFromRequest(HttpServletRequest request) {
		if (request == null) {
			return null;
		} else {
			String languageParam = "language";
			Locale locale = (Locale) request.getAttribute(languageParam);
			if (locale == null) {
				String language = request.getParameter(languageParam);
				if (StringUtils.isNotBlank(language)) {
					String country = "";
					int dash = language.indexOf('-');
					if (dash > -1) {
						country = language.substring(dash + 1).trim();
						language = language.substring(0,dash).trim();
						if (StringUtils.isNoneBlank(language, country)) {
							locale = new Locale(language, country);
						}
					}
				}
				if (locale == null) {
					locale = RequestContextUtils.getLocale(request);
				}
				request.setAttribute(languageParam, locale);
				return locale;
			} else {
				return locale;
			}
		}
	}
}
