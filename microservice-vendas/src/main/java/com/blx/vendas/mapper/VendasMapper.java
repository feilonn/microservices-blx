package com.blx.vendas.mapper;


import com.blx.vendas.dtos.VendasRequest;
import com.blx.vendas.dtos.VendasResponse;
import com.blx.vendas.models.Vendas;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendasMapper {

    private final ModelMapper modelMapper;

    public VendasResponse toVendasResponse(Vendas vendas) {
        return modelMapper.map(vendas, VendasResponse.class);
    }

    public Vendas toVendas(VendasRequest vendasRequest) {
        return modelMapper.map(vendasRequest, Vendas.class);
    }
}
