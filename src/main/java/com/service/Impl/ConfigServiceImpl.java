package com.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.IConfigDao;
import com.model.Config;
import com.service.IConfigService;

@Service
public class ConfigServiceImpl implements IConfigService  {
	 //使用@Autowired注释获取相应的对象
	@Autowired(required = false)
	private IConfigDao configDao;

	@Override
	public List<Config> SelectConfig(int AdminId) {
		// TODO Auto-generated method stub
		Config config = null;
		List<Config> list = null;
		List<Config> listb = null;
		try {
			//根据id获取数据
			config = configDao.SelectConfig(AdminId);
			//获取所有数据
			list = configDao.AllConfig();
			//设置条件获取数据
			Config configModel = new Config();
			configModel.setConfigId(1);
			listb = configDao.QueryConfig(configModel);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	    
}