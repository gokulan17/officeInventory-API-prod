package com.restApi.Inventory.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restApi.Inventory.Service.InventoryService;
import com.restApi.Inventory.Service.UserService;
import com.restApi.Inventory.model.InventoryDetail;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/officeInventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;

	@Autowired
	UserService userService;
	
	@GetMapping("/getall")
	public String getAllInventoryDetail() {
//		List<InventoryDetail> inventory = new ArrayList<InventoryDetail>();
//		inventory = inventoryService.getAllInventoryDetail();
		return "hello hi i am gokulans";
	}

	@GetMapping("/search")
	public List<InventoryDetail> getAllInventoryDetailFilter(@RequestParam("iname") String invName,
			@RequestParam("catry") String category, @RequestParam("sdate") String startdate,
			@RequestParam("edate") String endDate, @RequestParam("columnName") String cName,
			@RequestParam("sortValue") String sort, @RequestParam("limitval") int limitval,
			@RequestParam("offSetVal") int offSetval) {
		List<InventoryDetail> inventoryFilterValue = new ArrayList<InventoryDetail>();
		inventoryFilterValue = inventoryService.getAllInventoryDetailFilter(invName, category, startdate, endDate,
				cName, sort,limitval, offSetval);
		return inventoryFilterValue;
	}

	@GetMapping("/{id}")
	public List<InventoryDetail> getAllInventoryDetailById(InventoryDetail inventoryDetail) {
		List<InventoryDetail> inventory = new ArrayList<InventoryDetail>();
		inventory = inventoryService.getAllInventoryDetailById(inventoryDetail);
		return inventory;
	}

	@PostMapping("/add")
	public void createInventoryDetail(@RequestBody InventoryDetail inventoryDetail) {
		inventoryService.addInventoryService(inventoryDetail);
	}

	@PostMapping("/update")
	public void updateInventoryDetail(@RequestBody InventoryDetail inventoryDetail) {
		inventoryService.updateInventoryService(inventoryDetail);
	}

	@PostMapping("/delete")
	public void deleteInventoryDetail(@RequestBody InventoryDetail inventoryDetail) {
		System.out.println(inventoryDetail.getId());
		inventoryService.deleteInventoryService(inventoryDetail);
	}
	
	
	@PostMapping("/getData")
	public String  demo(@RequestBody InventoryDetail inventoryDetails,@RequestHeader(value="test") String token) throws Exception {
		//inventoryService.addInventoryService(inventoryDetail);
//		List<demodata> inventory = new ArrayList<demodata>();
//		inventory = inventoryService.getAllDetail();
//		System.out.println(inventory.size());
		
	
		userService.verifyToken(token);
		
		return "success";
	}
}
