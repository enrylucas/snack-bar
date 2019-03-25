package enry.snackbar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class SnackBarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnackBarApplication.class, args);
	}

}
