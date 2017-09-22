package com.aspone.brokerwebapp.domain.entity.converter;

import com.aspone.brokerwebapp.domain.entity.enumtype.Side;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SideConverter implements AttributeConverter<Side,Integer> {

    @Override
    public Integer convertToDatabaseColumn(Side attribute) {
        return attribute.getValue();
    }

    @Override
    public Side convertToEntityAttribute(Integer dbData) {
        return Side.valueOf(dbData);
    }
}
