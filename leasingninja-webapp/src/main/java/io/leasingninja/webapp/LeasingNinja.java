package io.leasingninja.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import io.leasingninja.vertrieb.infrastructure.VertragDatabaseEntity;

@SpringBootApplication(scanBasePackages="io.leasingninja.*")
//@EntityScan("io.leasingninja.*.domain")
@EntityScan("io.leasingninja.*.infrastructure")
//@EntityScan(basePackageClasses=VertragDatabaseEntity.class)
@EnableJpaRepositories("io.leasingninja.*.infrastructure") 
public class LeasingNinja {

	public static void main(String[] args) {
		SpringApplication.run(LeasingNinja.class, args);
	}

}
