package no.difi.sdp.testavsender.domain;

public enum MessageStatus {

	FAILED_RETRIEVING_PERSON_FROM_OPPSLAGSTJENESTE,
	FAILED_QUALIFYING_FOR_DIGITAL_POST,
	FAILED_SENDING_DIGITAL_POST,
	WAITING_FOR_RECEIPT,
	WAITING_FOR_OPENED_RECEIPT,
	FAILED_ACKNOWLEDGING_RETRIEVED_RECEIPT,
	FAILED_SENDING_FYSISK_POST,
    WAITING_FOR_DELIVERED_RECEIPT,
    DELIVER_MESSAGE_FAILED,
    SUCCESSFULLY_SENT_MESSAGE
	
}
