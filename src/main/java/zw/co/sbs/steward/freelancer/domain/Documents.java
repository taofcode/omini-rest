package zw.co.sbs.steward.freelancer.domain;

import org.apache.cxf.message.Attachment;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.ZonedDateTime;

import java.util.Map;

@Entity
public class Documents {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String fileUrl;
    private String documentType;
    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;
    private ZonedDateTime dateDeleted;
    private Accounts accounts;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public ZonedDateTime getZonedDateTimeCreated() {
        return dateCreated;
    }

    public void setZonedDateTimeCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ZonedDateTime getZonedDateTimeUpdated() {
        return dateUpdated;
    }

    public void setZonedDateTimeUpdated(ZonedDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public ZonedDateTime getZonedDateTimeDeleted() {
        return dateDeleted;
    }

    public void setZonedDateTimeDeleted(ZonedDateTime dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void setAccounts(Accounts accounts) {
        this.accounts = accounts;
    }



}
