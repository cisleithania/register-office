package edu.javacourse.register.domain;

import javax.persistence.*;
import java.util.List;

// человек женского пола

@Entity // этот класс является сущностью
@DiscriminatorValue("1") // установка значений для наследуемых объектов (женщина - 1)
public class PersonFemale extends Person {

    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "wife") // у человека мб несколько браков
    private List<MarriageCertificate> marriageCertificates;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "mother") // у человека мб несколько детей
    private List<BirthCertificate> birthCertificates;

    public List<MarriageCertificate> getMarriageCertificates() {
        return marriageCertificates;
    }

    public void setMarriageCertificates(List<MarriageCertificate> marriageCertificates) {
        this.marriageCertificates = marriageCertificates;
    }

    public List<BirthCertificate> getBirthCertificates() {
        return birthCertificates;
    }

    public void setBirthCertificates(List<BirthCertificate> birthCertificates) {
        this.birthCertificates = birthCertificates;
    }

}
