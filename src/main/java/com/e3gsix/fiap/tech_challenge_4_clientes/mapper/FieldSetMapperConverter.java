package com.e3gsix.fiap.tech_challenge_4_clientes.mapper;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;

public interface FieldSetMapperConverter<T> {
	BeanWrapperFieldSetMapper<T> convertFieldMapper(Class<T> type);
}