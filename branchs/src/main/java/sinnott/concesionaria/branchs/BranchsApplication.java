package sinnott.concesionaria.branchs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {
    "sinnott.concesionaria.branchs",
    "sinnott.concesionaria.commons"
})
public class BranchsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BranchsApplication.class, args);
	}
}
