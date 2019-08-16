package io.leasingninja.sales.ui;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Customer;

@Mapper(componentModel = "spring")
public interface MapStructContractModelMapper {

	MapStructContractModelMapper INSTANCE = Mappers.getMapper(MapStructContractModelMapper.class);
	
	ContractModel vertragToVertragModel(Contract contract);
	String vertragsnummer2String(ContractNumber number);
//	Vertragsnummer string2Vertragsnummer(String nummer);
	String fahrzeug2String(Car car);
//	Fahrzeug string2Fahrzeug(String fahrzeug);
	String map(Customer customer);
//	Kundenname map(String name);
}
