package io.leasingninja.riskmanagement.application;

import org.springframework.stereotype.Component;

@Component
public interface InboxApplicationService {

	void confirmSignedContract(String lvnr, int year, int month, int dayOfMonth);

}