package com.e3gsix.fiap.tech_challenge_4_clientes.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DomainProduct {

	private int code;
	private String description;
	private double price;
	private int quantity;
	private LocalDateTime date_inclusion;
}
