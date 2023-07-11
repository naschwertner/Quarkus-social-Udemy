package io.github.naschwertner.quarkussocial.rest;

import io.github.naschwertner.domain.model.User;
import io.github.naschwertner.quarkussocial.rest.quarkussocial.rest.CreateUserRequest;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)//consome json (especificação tipo de dado que a requisição vai receber)
@Produces(MediaType.APPLICATION_JSON) //retorna json
public class UserResource {

    @POST
    @Transactional
    public Response createUser( CreateUserRequest userRequest){
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        System.out.println("User before persist: " + user);
        //metodo do panache, a entidade tem todos os metodos para realizar a persistencia
        user.persist(); //persist metodo que salva entidade no bando de dados
        System.out.println("User after persist: " + user);
        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers(){
        PanacheQuery<PanacheEntityBase> query = User.findAll();  //ctrl alt v --> para criar variavel
        return Response.ok(query.list()).build();
    }
}
