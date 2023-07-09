package io.github.naschwertner.quarkussocial.rest;

import io.github.naschwertner.domain.model.User;
import io.github.naschwertner.quarkussocial.rest.quarkussocial.rest.CreateUserRequest;
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

        //metodo do panache, a entidade tem todos os metodos para realizar a persistencia
        user.persist(); //persist metodo que salva entidade no bando de dados

        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers(){
        return Response.ok().build();
    }
}
