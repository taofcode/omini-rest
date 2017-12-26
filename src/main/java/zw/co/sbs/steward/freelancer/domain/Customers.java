package zw.co.sbs.steward.freelancer.domain;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Entity
public class Customers {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;
    private String surname;
    private String idNumber;
    private String msisdn;
    private String email;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;
    private ZonedDateTime dateDeleted;
    @ManyToOne
    private User user;
    @Transient
    private List<Documents> documents;
    private String  agentId;
    @Transient
    private String accountNumber;
    @Transient
    private Map<String, Object> attachments;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ZonedDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(ZonedDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public ZonedDateTime getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(ZonedDateTime dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<Documents> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Documents> documents) {
        this.documents = documents;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Map<String, Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, Object> attachments) {
        this.attachments = attachments;
    }
}
