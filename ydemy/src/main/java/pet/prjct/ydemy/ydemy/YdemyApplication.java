package pet.prjct.ydemy.ydemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*@EntityScan(basePackages = {"pet.prjct.ydemy.ydemy"})
@ComponentScan(basePackages = {"pet.prjct.ydemy.ydemy.dao"})*/
public class YdemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(YdemyApplication.class, args);
	}

}
