package dataGenerator;

import com.github.javafaker.Faker;


public class UserPayloadGenerator {

    public Faker faker() {
        return Faker.instance();
    }

    public Integer generateUserId(){
        return faker().number().numberBetween(1,10);
    }

    public Integer generatePostLimiter(){
        return faker().number().numberBetween(1,100);
    }

}
