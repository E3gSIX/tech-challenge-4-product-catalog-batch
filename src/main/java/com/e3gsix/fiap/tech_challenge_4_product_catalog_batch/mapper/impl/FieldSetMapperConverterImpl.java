package com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.mapper.impl;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;

import com.e3gsix.fiap.tech_challenge_4_product_catalog_batch.mapper.FieldSetMapperConverter;

public class FieldSetMapperConverterImpl<T> implements FieldSetMapperConverter<T> {
	@Override
	public BeanWrapperFieldSetMapper<T> convertFieldMapper(Class<T> type) {
		BeanWrapperFieldSetMapper<T> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(type);
		return fieldSetMapper;
	}
}
