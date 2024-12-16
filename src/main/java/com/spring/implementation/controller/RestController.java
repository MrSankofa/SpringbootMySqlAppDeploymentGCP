package com.spring.implementation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.implementation.model.Bill;
import com.spring.implementation.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.implementation.model.Products;
import com.spring.implementation.model.ProductsDTO;
import com.spring.implementation.repository.ProductRepository;


@org.springframework.web.bind.annotation.RestController
public class RestController {

	private final BillService billService;
	@Autowired
	ProductRepository productRepo;

	public RestController(BillService billService) { this.billService = billService; }

	@GetMapping
	public List<Bill> getAllBills() {
		return billService.getAllBills();
	}

	@GetMapping("/api/bills/{id}")
	public Bill getBillById(@PathVariable Long id) {
		return billService.getBillById(id);
	}

	@PostMapping
	public Bill createBill(@RequestBody Bill bill) {
		return billService.saveBill(bill);
	}

	@PutMapping("/api/bills/{id}")
	public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
		bill.setId(id); // Ensure the ID is set for update
		return billService.saveBill(bill);
	}

	@DeleteMapping("/api/bills/{id}")
	public void deleteBill(@PathVariable Long id) {
		billService.deleteBill(id);
	}

	@PostMapping("/api/bills/batch-save")
	public List<Bill> saveBills(@RequestBody List<Bill> bills) {
		return billService.saveAllBills(bills);
	}


	@GetMapping("/welcome")
	public String welcome() {
		return "your rest endpoint works";
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody ProductsDTO productDTO) {
		Products product = new Products();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		Products prod = productRepo.save(product);
		return generateResponse("Items saved successfully!", HttpStatus.OK, prod);
	}

	 public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
	        Map<String, Object> map = new HashMap<String, Object>();
	            map.put("message", message);
	            map.put("status", status.value());
	            map.put("data", responseObj);

	            return new ResponseEntity<Object>(map,status);
	    }

	 @GetMapping("/getItems")
	 public ResponseEntity<Object> getItems(){
		 List<Products> items = productRepo.findAll();
		 return generateResponse("Complete Data!", HttpStatus.OK, items);
	 }


}
