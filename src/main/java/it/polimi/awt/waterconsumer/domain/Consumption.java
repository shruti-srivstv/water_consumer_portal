package it.polimi.awt.waterconsumer.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Consumption {
	
	public List<KeyValue> getUserConsumption(Consumption consumption){
		List<KeyValue> userconsumption = new ArrayList<Consumption.KeyValue>();
		userconsumption.add(new KeyValue("Russia", "17098242"));
		userconsumption.add(new KeyValue("Canada", "9984670"));
		userconsumption.add(new KeyValue("USA", "9826675"));
		userconsumption.add(new KeyValue("China", "9596961"));
		userconsumption.add(new KeyValue("Brazil", "8514877"));
		userconsumption.add(new KeyValue("Australia", "7741220"));
		userconsumption.add(new KeyValue("India", "3287263"));
		return userconsumption;
	}
	
	public static class KeyValue {
        String key;
        String value;
 
        public KeyValue(String key, String value) {
            super();
            this.key = key;
            this.value = value;
        }
 
        public String getKey() {
            return key;
        }
 
        public void setKey(String key) {
            this.key = key;
        }
 
        public String getValue() {
            return value;
        }
 
        public void setValue(String value) {
            this.value = value;
        }
 
    }


}
