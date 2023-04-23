package com.restApi.Inventory.InventoryDao;

import com.restApi.Inventory.model.User;

public interface userDao {
	
	public String userRegistration(User user);

	public User loginUser(User user);

}
