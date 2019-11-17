package edu.javacourse.register.domain;

import javax.persistence.*;
import java.time.LocalDate;

// паспорт человека

@Table(name = "ro_passport") // с какой таблицы доставать объекты, или сохранять их туда
@Entity // этот класс является сущностью, которую надо сохранять
public class Passport {

    // Mapping - какие поля в какие колонки прописать
    // любая сущность, которая сохраняется в БД, должна иметь первичный ключ (аннотация @Id)
    @Id // поле-идентификатор
    // генерация идентификатора (IDENTITY - при добавлении объекта в таблицу, автогенерирование Id, т.к. в Postgres есть автогенерация полей)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id") // название колонки в БД, на которую отображается поле
    private Long passportId;
    // cascade - множество операции, которые будут повторяться в ассоциированном объекте (REFRESH - обновление данных в БД)
    // fetch - чтение связанных объектов из БД (LAZY - загрузка, только при обращении)
    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY) // у паспорта(которых меняют в течение жизни) один владелец
    @JoinColumn(name = "person_id") // какая колонка нужна, чтобы вытащить person из БД
    private Person person; // человек, у которого есть паспорт
    @Column(name = "seria")
    private String seria;
    @Column(name = "number")
    private String number;
    @Column(name = "date_issue")
    private LocalDate issueDate; // когда выдан
    @Column(name = "issue_department")
    private String issueDepartment; // кем выдан

    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
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

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
