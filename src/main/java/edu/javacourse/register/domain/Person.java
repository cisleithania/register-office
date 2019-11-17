package edu.javacourse.register.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

// человек

@Table(name = "ro_person") // с какой таблицы доставать объекты, или сохранять их туда
@Entity // этот класс является сущностью, которую надо сохранять
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // стратегия наследования (SINGLE_TABLE - наследование отдельных таблиц)
// по какой колонке отличаются объекты (название столбца, целое число)
@DiscriminatorColumn(name = "sex", discriminatorType = DiscriminatorType.INTEGER)
// именованный запрос Person.findPersons
@NamedQueries({
    @NamedQuery(name = "Person.findPersons",
    query =  "SELECT  p FROM Person p " +
            "LEFT JOIN FETCH p.passports " +
            "LEFT JOIN FETCH p.birthCertificate " +
            "WHERE p.personId = :personId")
})
public class Person {

    // Mapping - какие поля в какие колонки прописать
    // любая сущность, которая сохраняется в БД, должна иметь первичный ключ (аннотация @Id)

    @Id // поле-идентификатор
    // генерация идентификатора (IDENTITY - при добавлении объекта в таблицу, автогенерирование Id, т.к. в Postgres есть автогенерация полей)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id") // название колонки в БД, на которую отображается поле
    private Long personId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "date_birth")
    private LocalDate dateOfBirth;
    // mappedBy - поле person у класса BirthCertificate
    @OneToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "person")
    private BirthCertificate birthCertificate;
    // cascade - множество операции, которые будут повторяться в ассоциированном объекте (REFRESH - обновление данных в БД)
    // fetch - чтение связанных объектов из БД (LAZY - загрузка, только при обращении)
    // mappedBy - ссылается на имя свойства связи на стороне владельца (person - поле в классе Passport)
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "person") // у человека мб несколько паспортов
    private List<Passport> passports; // список паспортов человека

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BirthCertificate getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(BirthCertificate birthCertificate) {
        this.birthCertificate = birthCertificate;
    }

    public List<Passport> getPassports() {
        return passports;
    }

    public void setPassports(List<Passport> passports) {
        this.passports = passports;
    }

}
