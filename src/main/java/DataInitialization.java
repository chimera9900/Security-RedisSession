import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.developer.storesws.model.Store;
import com.developer.storesws.service.StoreService;

@Component
public class DataInitialization implements CommandLineRunner{
	@Autowired
	StoreService storeService;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		storeService.addStore(new Store("http://google.com", "good website"));
		storeService.addStore(new Store("http://yahoo.com", "good website"));
		
	}

}
