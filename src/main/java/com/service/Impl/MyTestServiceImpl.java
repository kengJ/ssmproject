package com.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IMyTestDao;
import com.model.SortModel;
import com.service.IMyTestService;

@Service("myTestImpl")
public class MyTestServiceImpl implements IMyTestService  {
	 //使用@Autowired注释获取相应的对象
	@Autowired(required = false)
	private IMyTestDao myTestdao;

	    public List<SortModel> selectSort(int id) {

	        SortModel model = null;
	        List<SortModel> list = null;
	        List<SortModel> listb = null;
	        try {
	            
	            //根据ID获取Sort表的数据
	            model = myTestdao.selectSort(id);
	            
	            //获取Sort表所有的数据
	            list = myTestdao.allSort();
	            
	            //根据多条件条件获取Sort表的数据
	            SortModel qmodel = new SortModel();
	            qmodel.setId(1);
	            listb = myTestdao.querySort(qmodel);

	        }catch (Exception e)
	        {
	            e.printStackTrace();
	        }

	        //返回Sort表所有的数据，并转换成json字符串
	        return list;
}
}