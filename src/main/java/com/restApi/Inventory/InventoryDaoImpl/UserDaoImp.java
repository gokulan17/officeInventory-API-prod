package com.restApi.Inventory.InventoryDaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.restApi.Inventory.InventoryDao.userDao;
import com.restApi.Inventory.UtilClass.Constant;
import com.restApi.Inventory.model.User;

@Repository
public class UserDaoImp implements userDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public String userRegistration(User user) {
		// TODO Auto-generated method stub
		
		String sql="insert into user (name,password,role,created_by) values (?,?,?,?)";
		
		Integer insert=jdbcTemplate.update(sql,user.getName(),user.getPassword(),user.getRole(),user.getName());
		
		
		String jsonResponse=Constant.MSG_STATUS_FAILURE;
		
		if(insert>=1) {
		jsonResponse=Constant.MSG_LOGIN_SUCCESS;	
		}
		
		return jsonResponse;
	}

	@Override
	public User loginUser(User user) {

		String sql="select password from user where name=?";
		User response=null;
		try {
			RowMapper<User> rowmapper=new BeanPropertyRowMapper<User>(User.class);
			response =jdbcTemplate.queryForObject(sql, rowmapper,user.getName());
		}catch(EmptyResultDataAccessException empty) {
			
			response=null;
		}catch(Exception e) {
			response=null;
		}
		
		
		
		
		
		return response;
	}

}
