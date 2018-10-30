package hello;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
//import net.rossillo.spring.web.mvc.CacheControl;

@Controller
@Slf4j
public class CachingController {

	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/getRecords1")
	//@CacheControl(maxAge=31556926)
	public Customer get(@RequestParam String name,Model model ) {
		//System.out.println("gshp "+ht.ETAG+"    valuve"+ht.ETAG.);
		
		log.info("Getting Records");
		log.info(""+Instant.now());
		try {
			Thread.sleep(10000);
			return customerRepository.findByFirstName(name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/getRecords2")
	//@CacheControl(maxAge=31556926)
	public ResponseEntity<Customer> getUser2(@RequestParam String name) { 
	    
		try {
			Thread.sleep(10000);
			//return customerRepository.findByFirstName(name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
	      .cacheControl(CacheControl.maxAge(1800, TimeUnit.SECONDS).cachePublic())
	      .body(customerRepository.findByFirstName(name));
	}

	
	@GetMapping("/getRecords")
	//@CacheControl(maxAge=31556926)
	public @ResponseBody ResponseEntity<Customer> getUser(@RequestParam String name) { 
	    
		try {
			System.out.println("its coming.....");
			Thread.sleep(1000);
			//return customerRepository.findByFirstName(name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
	      .cacheControl(CacheControl.maxAge(30, TimeUnit.SECONDS).cachePublic())
	      .body(customerRepository.findByFirstName(name));
	}
	
	@GetMapping("/getform")
	//@CacheControl(maxAge=31556926)
	public String getform() { 
	
		
		return "cache";
	}
}
