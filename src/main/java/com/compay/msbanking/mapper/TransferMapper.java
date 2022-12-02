package com.compay.msbanking.mapper;

import com.compay.msbanking.dto.request.TransferRequest;
import com.compay.msbanking.dto.response.TransferResponse;
import com.compay.msbanking.entity.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferMapper {

    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);

    Transfer requestTransferToTransfer(TransferRequest transferRequest);

    TransferResponse transferToTransferResponse(Transfer transfer);

}
