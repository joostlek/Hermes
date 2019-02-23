package nl.jtosti.hermes.user;

import nl.jtosti.hermes.company.Company;
import nl.jtosti.hermes.image.Image;
import nl.jtosti.hermes.security.Argon2PasswordEncoder;
import nl.jtosti.hermes.security.user.ApplicationUser;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.*;

/**
 * This represents an user of the application
 */
@Entity(name = "users")
public class User {

    private static final PasswordEncoder PASSWORD_ENCODER = new Argon2PasswordEncoder();

    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private Set<Company> companies = new HashSet<>();

    @OneToMany(mappedBy = "uploader")
    private Set<Image> images = new HashSet<>();

    protected User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPassword(password);
        this.companies = new HashSet<>();
        this.images = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @param password hashes the password and saves the hash
     */
    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public void addCompany(Company company) {
        this.companies.add(company);
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    void addImage(Image image) {
        this.images.add(image);
    }

    @Override
    public String toString() {
        if (this.id == null) {
            return String.format("<User: %s %s>", this.firstName, this.lastName);
        } else {
            return String.format("<User %s: %s %s>", this.id, this.firstName, this.lastName);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return this.firstName.equals(user.getFirstName()) && this.lastName.equals(user.getLastName()) && Objects.equals(this.id, user.getId()) && this.email.equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public UserDetails toUserDetails() {
        return new ApplicationUser(this.email, this.getPassword(), this.roles);
    }
}
