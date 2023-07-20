package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import org.springframework.web.service.annotation.GetExchange;

@Embeddable
@Getter //Setter는 제공하지 않는다
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() { //함부로 new로 생성하면 안돼! 라는 의미, protected로 하는 것이 안전함
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
