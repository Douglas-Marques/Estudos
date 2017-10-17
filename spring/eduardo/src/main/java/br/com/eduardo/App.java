package br.com.eduardo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@ComponentScan(basePackageClasses={ProdutoController.class, ProdutoService.class, ProdutoRepository.class})
@SpringBootApplication
public class App {
    public static void main( String[] args ) throws Exception{
        SpringApplication.run(App.class, args);
    }
}

//https://www.sitepoint.com/api-calls-angularjs-http-service/
