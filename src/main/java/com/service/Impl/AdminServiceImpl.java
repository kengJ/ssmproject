package com.service.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.IAdminDao;
import com.model.Admin;
import com.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService  {
	 //使用@Autowired注释获取相应的对象
	@Autowired(required = false)
	private IAdminDao adminDao;

	@Override
	public List<Admin> SelectAdmin(int AdminId) {
		// TODO Auto-generated method stub
		Admin admin = null;
		List<Admin> list = null;
		List<Admin> listb = null;
		try {
			//根据id获取数据
			admin = adminDao.SelectAdmin(AdminId);
			//获取所有数据
			list = adminDao.AllAdmin();
			//设置条件获取数据
			Admin adminModel = new Admin();
			adminModel.setAdminId(1);
			listb = adminDao.QueryAdmin(adminModel);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	    
}