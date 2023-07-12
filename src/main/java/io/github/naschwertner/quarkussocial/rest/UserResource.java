package io.github.naschwertner.quarkussocial.rest;

import io.github.naschwertner.domain.model.User;
import io.github.naschwertner.quarkussocial.rest.domain.repository.UserRepository;
import io.github.naschwertner.quarkussocial.rest.quarkussocial.rest.CreateUserRequest;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Set;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)//consome json (especificação tipo de dado que a requisição vai receber)
@Produces(MediaType.APPLICATION_JSON) //retorna json
public class UserResource {

    private UserRepository repository;
    private Validator validator;

    //criando construtor para UserResource
    @Inject
    public UserResource(UserRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }
    @POST
    @Transactional
    public Response createUser( CreateUserRequest userRequest){

        Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);
        if(!violations.isEmpty()){
            ConstraintViolation<CreateUserRequest> erro = violations.stream().findAny().get();
            String errorMessage = erro.getMessage();
            return Response.status(400).entity(errorMessage).build();
        }

        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        //metodo do panache, a entidade tem todos os metodos para realizar a persistencia
        repository.persist(user); //persist metodo que salva entidade no bando de dados
        System.out.println("User after persist: " + user);
        return Response.ok(user).build();
    }

    @GET
    public Response listAllUsers(){
        PanacheQuery<User> query = repository.findAll();  //ctrl alt v --> para criar variavel
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("/{id}") //quando foi realizado delete precisa ser passado id como parametro
    //  /user/2
    @Transactional
    public Response deleteUser( @PathParam("id") Long id){
        User user = repository.findById(id);

        //conferindo se o id passado como parametro existe, caso contrario retorna NOT FOUND
        if (user != null) {
            repository.delete(user);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUser( @PathParam("id") Long id, CreateUserRequest userData){
        User user = repository.findById(id); //procurando usuario por id

        if(user != null){ //se usuario diferente de nulo
            user.setName(userData.getName()); //seta o nome passado como parametro no body requisição
            user.setAge(userData.getAge()); //seta a idade passada como parametro no body da requisição
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build(); // se nao encontrar o id passado volta 404 not found

    }
}
