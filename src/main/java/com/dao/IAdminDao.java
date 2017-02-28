package com.dao;

import java.util.List;
import com.model.Admin;

public interface IAdminDao {
	Admin SelectAdmin(int AdminId);
    List<Admin> AllAdmin();
    List<Admin> QueryAdmin(Admin admin);
}
