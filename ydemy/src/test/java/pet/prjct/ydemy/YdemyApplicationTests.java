package pet.prjct.ydemy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class YdemyApplicationTests {

	public static void main(String[] args) {
		String str = "{bcrypt}$2a$10$BsTt7WQ0b2QIFpE/HRBkEODhJKGfovaryh7yEJmurG/.vAsbjwJCy";
		System.out.println(str.length());
	}



}
