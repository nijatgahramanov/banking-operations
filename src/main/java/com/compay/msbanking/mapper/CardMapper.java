package com.compay.msbanking.mapper;

import com.compay.msbanking.dto.request.CardRequest;
import com.compay.msbanking.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CardMapper {

    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);

    Card requestCardToCard(CardRequest requestCard);
}
