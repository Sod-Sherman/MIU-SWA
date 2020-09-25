package edu.miu.ownerservice;

import kafka.Owner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class OwnerServiceApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(OwnerServiceApplication.class, args);
    }

    Map<String, Owner> ownerMap = new HashMap<>();

    @Override
    public void run(String... args) throws Exception {


        Owner puujgee = new Owner("EE1000", "Puujee", "100-333-2222");
        Owner sodoo = new Owner("BB1000", "Sodoo", "300-333-2222");
        Owner tuugii = new Owner("ES1000", "Tuugii", "400-333-2222");
        Owner kanjie = new Owner("BS1000", "Kaanjii", "500-333-2222" );


        ownerMap.put("EE1001", puujgee);
        ownerMap.put("BB1001", sodoo);
        ownerMap.put("ES1001", tuugii);
        ownerMap.put("BS1001", kanjie);

    }

    @Bean
    public OwnerRepository ownerRepository() {
        OwnerRepository ownerRepository = new OwnerRepository();
        ownerRepository.setOwnerMap(ownerMap);
        return ownerRepository;
    }

    @Bean
    public FeeRecord feeRecord(){
        return new FeeRecord(ownerRepository());
    }
}
