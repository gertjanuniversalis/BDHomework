package GJ.EE.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student {
    @Id
    @GeneratedValue
    public int Id;

    public String firstName;
    public String lastName;
    public int studentNumber;
    public int yearOfBirth;
}
