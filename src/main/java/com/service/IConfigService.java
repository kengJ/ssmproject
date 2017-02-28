package com.service;

import java.util.List;
import com.model.Config;


public interface IConfigService {
	List<Config> SelectConfig(int AdminId);
}
