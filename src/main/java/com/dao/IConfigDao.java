package com.dao;

import java.util.List;
import com.model.Config;

public interface IConfigDao {
	Config SelectConfig(int ConfigId);
    List<Config> AllConfig();
    List<Config> QueryConfig(Config Config);
}
