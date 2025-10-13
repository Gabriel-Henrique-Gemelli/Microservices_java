package br.edu.atitus.product_service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_product")
@Data
@Getter
@Setter
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private String brand;
	private double price;
	private String currency;
	private Integer stock;
	
	@Transient
	private String environment;
	@Transient
	private double convertedPrice;
	
	
	
	

}
