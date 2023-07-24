package io.github.naschwertner.domain.model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;


@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column opcional, colocado quando o nome da coluna é diferente do nome da propriedade
    @Column(name = "name") //aqui não haveria necessidade, colocado apenas para exemplificar
    private String name;
    @Column (name = "age")
    private Integer age;



    public void setAge(Integer age) {
        this.age = age;
    }

    // Métodos para comparação de objetos:
    // hashCode(): Este método é usado para obter um valor de hash inteiro para o objeto atual.
    // equals(Object obj): Este método é usado para verificar se dois objetos são considerados "iguais".
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}
