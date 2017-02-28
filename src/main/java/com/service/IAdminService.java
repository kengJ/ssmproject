package com.service;

import java.util.List;
import com.model.Admin;


public interface IAdminService {
	List<Admin> SelectAdmin(int AdminId);
}
