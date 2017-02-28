package com.model;

public class Config {
	private Integer ConfigId;
	private String ConfigKey;
	private String ConfigValue;
	public Integer getConfigId() {
		return ConfigId;
	}
	public void setConfigId(Integer configId) {
		ConfigId = configId;
	}
	public String getConfigKey() {
		return ConfigKey;
	}
	public void setConfigKey(String configKey) {
		ConfigKey = configKey;
	}
	public String getConfigValue() {
		return ConfigValue;
	}
	public void setConfigValue(String configValue) {
		ConfigValue = configValue;
	}
	@Override
	public String toString() {
		return "Config [ConfigId=" + ConfigId + ", ConfigKey=" + ConfigKey + ", ConfigValue=" + ConfigValue + "]";
	}
}
