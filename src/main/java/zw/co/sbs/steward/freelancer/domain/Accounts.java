package zw.co.sbs.steward.freelancer.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
public class Accounts implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String accountType;
    private String accountName;
    private String accountNumber;
    @ManyToOne
    private Customers customers;
    @OneToMany
    private Set<Documents> documents;

    private ZonedDateTime dateCreated;
    private ZonedDateTime dateUpdated;
    private ZonedDateTime dateDeleted;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Set<Documents> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Documents> documents) {
        this.documents = documents;
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
}
