package com.liao.model.entity.system;

import com.liao.model.entity.base.BaseEntity;
import java.util.Date;
import java.util.Objects;


public class SysOperLog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String title;

	private String businessType;

	private String method;

	private String requestMethod;

	private String operatorType;

	private String operName;

	private String deptName;

	private String operUrl;

	private String operIp;

	private String operParam;

	private String jsonResult;

	private Integer status;

	private String errorMsg;

	private Date operTime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOperUrl() {
		return operUrl;
	}

	public void setOperUrl(String operUrl) {
		this.operUrl = operUrl;
	}

	public String getOperIp() {
		return operIp;
	}

	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}

	public String getOperParam() {
		return operParam;
	}

	public void setOperParam(String operParam) {
		this.operParam = operParam;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Date getOperTime() {
		return operTime;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		SysOperLog that = (SysOperLog) o;
		return Objects.equals(title, that.title) && Objects.equals(businessType, that.businessType) && Objects.equals(method, that.method) && Objects.equals(requestMethod, that.requestMethod) && Objects.equals(operatorType, that.operatorType) && Objects.equals(operName, that.operName) && Objects.equals(deptName, that.deptName) && Objects.equals(operUrl, that.operUrl) && Objects.equals(operIp, that.operIp) && Objects.equals(operParam, that.operParam) && Objects.equals(jsonResult, that.jsonResult) && Objects.equals(status, that.status) && Objects.equals(errorMsg, that.errorMsg) && Objects.equals(operTime, that.operTime);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), title, businessType, method, requestMethod, operatorType, operName, deptName, operUrl, operIp, operParam, jsonResult, status, errorMsg, operTime);
	}

	@Override
	public String toString() {
		return "SysOperLog{" +
				"title='" + title + '\'' +
				", businessType='" + businessType + '\'' +
				", method='" + method + '\'' +
				", requestMethod='" + requestMethod + '\'' +
				", operatorType='" + operatorType + '\'' +
				", operName='" + operName + '\'' +
				", deptName='" + deptName + '\'' +
				", operUrl='" + operUrl + '\'' +
				", operIp='" + operIp + '\'' +
				", operParam='" + operParam + '\'' +
				", jsonResult='" + jsonResult + '\'' +
				", status=" + status +
				", errorMsg='" + errorMsg + '\'' +
				", operTime=" + operTime +
				'}';
	}
}