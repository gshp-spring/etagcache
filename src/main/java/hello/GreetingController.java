package hello;

import java.time.Instant;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class GreetingController {

	@Autowired
	CustomerRepository customerRepository;
	@Bean
	Filter shallowEtagFilter() {
		return new ShallowEtagHeaderFilter();
	}
	
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        log.info(" "+Instant.now());
        try {
			Thread.sleep(10l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "greeting";
    }

    @GetMapping("/greeting1form")
	//@CacheControl(maxAge=31556926)
	public String getform() { 
	
		
		return "greeting1";
	}
    
    @GetMapping("/getRecordsGreeting")
	//@CacheControl(maxAge=31556926)
    public @ResponseBody Customer get(@RequestParam String name,Model model ) {
		//System.out.println("gshp "+ht.ETAG+"    valuve"+ht.ETAG.);
		
		log.info("getRecordsGreeting ........Getting Records");
		log.info(""+Instant.now());
		try {
			Thread.sleep(100);
			return customerRepository.findByFirstName(name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
    
}
