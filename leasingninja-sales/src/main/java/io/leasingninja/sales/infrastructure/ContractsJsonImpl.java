package io.leasingninja.sales.infrastructure;

import com.google.gson.Gson;

import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;

public class ContractsJsonImpl implements Contracts  {

	@Override
	public void save(Contract contract) {
		String serializedContract = new Gson().toJson(contract);
		System.out.println("ContractRepositoryJsonImpl: Vertrag als JSON: " + serializedContract);
	}

//	@Override  //JACKSON
//	public void save(LeasingVertrag leasingVertrag) {
//		var mapper = new ObjectMapper();
//		
//		try {
//			mapper.writeValue(System.out, leasingVertrag);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
	@Override
	public Contract with(ContractNumber number) {
		// TODO hier weitermachen mit: https://vaughnvernon.co/?p=942
		return null;
	}

}
