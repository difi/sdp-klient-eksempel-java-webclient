package no.difi.sdp.webclient.domain;

import no.difi.begrep.Reservasjon;
import no.difi.begrep.Status;
import no.difi.sdp.client.domain.Prioritet;
import no.difi.sdp.webclient.validation.Ssn;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestSentDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date responseReceivedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date completedDate;
	
	@Ssn
	private String ssn;

	@OneToOne(cascade = CascadeType.ALL)
	private Document document;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "message") 
	private Set<Document> attachments;
	
	@NotNull
	private String senderOrgNumber;
	
	private String senderId;
	
	private String invoiceReference;
	
	@NotNull
	private String keyPairAlias;

	@Enumerated(EnumType.STRING)
	@NotNull
	private Prioritet priority;
	
	@NotNull
	private String languageCode;
	
	@Enumerated(EnumType.STRING)
	private Status contactRegisterStatus;
	
	@Enumerated(EnumType.STRING)
	private Reservasjon reservationStatus;
	
	private String email;
	
	private String mobile;
	
	@Lob
	private byte[] postboxCertificate;
	
	private String postboxAddress;
	
	private String postboxVendorOrgNumber;
	
	private String conversationId;
	
	@Lob
	private String xmlRetrievePersonsRequest;
	
	@Lob
	private String xmlRetrievePersonsRequestPayload;
	
	@Lob
	private String xmlRetrievePersonsResponse;
	
	@Lob
	private String xmlRetrievePersonsResponsePayload;
	
	@Lob
	private String xmlSendMessageRequest;
	
	@Lob
	private String xmlSendMessageRequestPayload;
	
	@Lob
	private String xmlSendMessageResponse;
	
	private MessageStatus status;
	
	@Lob
	private String exception;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "message")
	private Set<Receipt> receipts;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] asic;
	
	private boolean retrieveContactDetails;
	
	private boolean saveBinaryContent;

	@Embedded
	private DigitalPost digitalPost;

	@Embedded
	private FysiskPost fysiskPost;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getRequestSentDate() {
		return requestSentDate;
	}

	public void setRequestSentDate(Date requestSentDate) {
		this.requestSentDate = requestSentDate;
	}

	public Date getResponseReceivedDate() {
		return responseReceivedDate;
	}

	public void setResponseReceivedDate(Date responseReceivedDate) {
		this.responseReceivedDate = responseReceivedDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	
	public String getSsn() {
		return ssn;
	}
	
	public void setSsn(String ssn) {
        this.ssn = ssn;
    }

	public Document getDocument() {
		return document;
	}
	
	public void setDocument(Document document) {
        this.document = document;
    }
	
	public Set<Document> getAttachments() {
		return attachments;
	}
	
	public void setAttachments(Set<Document> attachments) {
		this.attachments = attachments;
	}
	
	public String getSenderOrgNumber() {
		return senderOrgNumber;
	}

	public void setSenderOrgNumber(String senderOrgNumber) {
		this.senderOrgNumber = senderOrgNumber;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	public String getInvoiceReference() {
		return invoiceReference;
	}

	public void setInvoiceReference(String invoiceReference) {
		this.invoiceReference = invoiceReference;
	}
	
	public String getKeyPairAlias() {
		return keyPairAlias;
	}

	public void setKeyPairAlias(String keyPairAlias) {
		this.keyPairAlias = keyPairAlias;
	}

	public Prioritet getPriority() {
		return priority;
	}

	public void setPriority(Prioritet priority) {
		this.priority = priority;
	}
	
	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
	public Status getContactRegisterStatus() {
		return contactRegisterStatus;
	}

	public void setContactRegisterStatus(Status contactRegisterStatus) {
		this.contactRegisterStatus = contactRegisterStatus;
	}

	public Reservasjon getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(Reservasjon reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public byte[] getPostboxCertificate() {
		return postboxCertificate;
	}

	public void setPostboxCertificate(byte[] postboxCertificate) {
		this.postboxCertificate = postboxCertificate;
	}

	public String getPostboxAddress() {
		return postboxAddress;
	}

	public void setPostboxAddress(String postboxAddress) {
		this.postboxAddress = postboxAddress;
	}

	public String getPostboxVendorOrgNumber() {
		return postboxVendorOrgNumber;
	}

	public void setPostboxVendorOrgNumber(String postboxVendorOrgNumber) {
		this.postboxVendorOrgNumber = postboxVendorOrgNumber;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}
	
	public String getXmlRetrievePersonsRequest() {
		return xmlRetrievePersonsRequest;
	}

	public void setXmlRetrievePersonsRequest(String xmlRetrievePersonsRequest) {
		this.xmlRetrievePersonsRequest = xmlRetrievePersonsRequest;
	}

	public String getXmlRetrievePersonsRequestPayload() {
		return xmlRetrievePersonsRequestPayload;
	}

	public void setXmlRetrievePersonsRequestPayload(String xmlRetrievePersonsRequestPayload) {
		this.xmlRetrievePersonsRequestPayload = xmlRetrievePersonsRequestPayload;
	}

	public String getXmlRetrievePersonsResponse() {
		return xmlRetrievePersonsResponse;
	}

	public void setXmlRetrievePersonsResponse(String xmlRetrievePersonsResponse) {
		this.xmlRetrievePersonsResponse = xmlRetrievePersonsResponse;
	}

	public String getXmlRetrievePersonsResponsePayload() {
		return xmlRetrievePersonsResponsePayload;
	}

	public void setXmlRetrievePersonsResponsePayload(String xmlRetrievePersonsResponsePayload) {
		this.xmlRetrievePersonsResponsePayload = xmlRetrievePersonsResponsePayload;
	}

	public String getXmlSendMessageRequest() {
		return xmlSendMessageRequest;
	}

	public void setXmlSendMessageRequest(String xmlSendMessageRequest) {
		this.xmlSendMessageRequest = xmlSendMessageRequest;
	}

	public String getXmlSendMessageRequestPayload() {
		return xmlSendMessageRequestPayload;
	}

	public void setXmlSendMessageRequestPayload(String xmlSendMessageRequestPayload) {
		this.xmlSendMessageRequestPayload = xmlSendMessageRequestPayload;
	}

	public String getXmlSendMessageResponse() {
		return xmlSendMessageResponse;
	}

	public void setXmlSendMessageResponse(String xmlSendMessageResponse) {
		this.xmlSendMessageResponse = xmlSendMessageResponse;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}
	
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
	public Set<Receipt> getReceipts() {
		return receipts;
	}
	
	public void setReceipts(Set<Receipt> receipts) {
		this.receipts = receipts;
	}

	public byte[] getAsic() {
		return asic;
	}

	public void setAsic(byte[] asice) {
		this.asic = asice;
	}

	public boolean getRetrieveContactDetails() {
		return retrieveContactDetails;
	}

	public void setRetrieveContactDetails(boolean retrieveContactDetails) {
		this.retrieveContactDetails = retrieveContactDetails;
	}

	public boolean getSaveBinaryContent() {
		return saveBinaryContent;
	}

	public void setSaveBinaryContent(boolean saveBinaryContent) {
		this.saveBinaryContent = saveBinaryContent;
	}

	public DigitalPost getDigitalPost() {
		if(digitalPost == null){
			setDigitalPost(new DigitalPost());
		}
		return digitalPost;
	}

	public void setDigitalPost(DigitalPost digitalPost) {
		this.digitalPost = digitalPost;
	}

	public FysiskPost getFysiskPost() {
		if(fysiskPost == null){
			setFysiskPost(new FysiskPost());
		}
		return fysiskPost;
	}

	public void setFysiskPost(FysiskPost fysiskPost) {
		this.fysiskPost = fysiskPost;
	}
}
