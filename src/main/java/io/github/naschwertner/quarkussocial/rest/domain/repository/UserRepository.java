package io.github.naschwertner.quarkussocial.rest.domain.repository;

import io.github.naschwertner.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped//cria uma instancia dessa classe/repositorio,
// dentro do contexto da aplicação para que eu possa utilizar onde for desejado
public class UserRepository implements PanacheRepository<User> {



}
