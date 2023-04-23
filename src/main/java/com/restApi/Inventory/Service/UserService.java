package com.restApi.Inventory.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restApi.Inventory.InventoryDao.userDao;
import com.restApi.Inventory.UtilClass.Constant;
import com.restApi.Inventory.UtilClass.UtilClass;
import com.restApi.Inventory.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class UserService {
	
	
	@Autowired
	userDao userDao;
	
	
	
	public String SaveUserRegistration(User user) {
	
		String jsonResponse=userDao.userRegistration(user);
		
		
		
		
		
	
		return jsonResponse;
	}



	
	public String userLogin(User user) {

		User jsonResponse=userDao.loginUser(user);
		
		String response=Constant.MSG_STATUS_FAILURE;
		
		long sytemTime=System.currentTimeMillis();
		
		long durationtime=60*60;
		
		long expiredtime=sytemTime+durationtime*1000;
		
		
		
		if(jsonResponse!=null && jsonResponse.getPassword().equalsIgnoreCase(user.getPassword())) {
			response=Constant.MSG_LOGIN_SUCCESS;
			
			Claims claims=Jwts.claims()
					.setIssuer(user.getName())
					.setIssuedAt(UtilClass.getCurrentDateTime())
					.setExpiration(new Date(expiredtime));
			
			claims.put("role", user.getRole());
			
			Key keyvalue = null;
			try {
				keyvalue = generateKey();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

			return Jwts.builder().setClaims(claims)
					.signWith(key)
					.compact();
			
		}
		
		
		return response;
	}
	
	 private static SecretKey generateKey() throws NoSuchAlgorithmException {
	        KeyGenerator keyGenerator = KeyGenerator.getInstance("HMACSHA256");
	        return keyGenerator.generateKey();
	    }
	 
	 
	public void verifyToken(String headderToken) throws Exception {
		
		
		try {
			
	        Key key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);


			
			System.out.println(headderToken);
			
			 Jws<Claims> jws = Jwts.parserBuilder()
	                    .setSigningKey(key)
	                    .build()
	                    .parseClaimsJws(headderToken);

	            // Get the claims from the parsed token
//	            Claims claims = jws.getBody();

	            // Print the claims
//	            System.out.println(claims);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception("invalid token");
		}
		
		
		
		
	}      

}
