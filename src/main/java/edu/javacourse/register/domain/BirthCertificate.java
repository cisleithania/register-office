package edu.javacourse.register.domain;

import javax.persistence.*;
import java.time.LocalDate;

// свидетельство о рождении
@Table(name = "ro_birth_certificate") // с какой таблицы доставать объекты, или сохранять их туда
@Entity // этот класс является сущностью
public class BirthCertificate {

    @Id // поле-идентификатор
    // генерация идентификатора (IDENTITY - при добавлении объекта в таблицу, автогенерирование Id, т.к. в Postgres есть автогенерация полей)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birth_certificate_id")
    private Long birthCertificateId;
    @Column(name = "number_certificate") // название колонки в БД, на которую отображается поле
    private String number;
    @Column(name = "date_issue")
    private LocalDate issueDate;
    @OneToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id") // какая колонка нужна, чтобы вытащить person из БД (ссылка на персону в сертификате)
    private Person person;
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "father_id")
    private PersonMale father;
    @ManyToOne
    @JoinColumn(name = "mother_id")
    private PersonFemale mother;

    public Long getBirthCertificateId() {
        return birthCertificateId;
    }

    public void setBirthCertificateId(Long birthCertificateId) {
        this.birthCertificateId = birthCertificateId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonMale getFather() {
        return father;
    }

    public void setFather(PersonMale father) {
        this.father = father;
    }

    public PersonFemale getMother() {
        return mother;
    }

    public void setMother(PersonFemale mother) {
        this.mother = mother;
    }
}
