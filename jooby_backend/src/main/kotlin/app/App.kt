package app

import controller.Controller
import controller.UserController
import graphql.scalars.ExtendedScalars
import graphql.schema.idl.RuntimeWiring
import graphql.schema.idl.TypeRuntimeWiring.newTypeWiring
import graphqlFetcher.DataFetcherBuilder
import io.jooby.Kooby
import io.jooby.runApp

//setting hibernation with migrations an modules
import io.jooby.hibernate.HibernateModule
import io.jooby.hibernate.TransactionalRequest
import io.jooby.hikari.HikariModule
import io.jooby.json.JacksonModule
import io.jooby.flyway.FlywayModule

import io.jooby.graphql.GraphQLModule
//import io.jooby.graphql.GraphQLPlaygroundModule is bugged right now
import io.jooby.graphql.GraphiQLModule

import model.User

import io.jooby.require
import javax.persistence.EntityManager

class App: Kooby({

  //installing all packages that will be used the in the project
  install(JacksonModule())

  install(HikariModule())

  install(FlywayModule())

  install(HibernateModule(User::class.java))

  //Open session in view filter (entitymanager + transaction):
  decorator(TransactionalRequest())

  //jooby's entityManager must be declared in the install class and will
  //be passed around to various controllers to perform database operations
  val entityManager = require(EntityManager::class)

  //setting up GraphQL
  install(GraphQLModule(
          RuntimeWiring.newRuntimeWiring()
                  .type(newTypeWiring("Query")
                          .dataFetchers(DataFetcherBuilder().buildQueryDataFetchers()))
                  .type(newTypeWiring("Mutation")
                          .dataFetchers(DataFetcherBuilder().buildMutationDataFetcher()))
                  .scalar(ExtendedScalars.Date)
                  .scalar(ExtendedScalars.DateTime)
                  .build())
  )


  //install(GraphQLPlaygroundModule())
  install(GraphiQLModule())

  //declaring all controllers that will be used in project
  mvc(Controller())
  mvc(UserController(entityManager))
})

fun main(args: Array<String>) {
  runApp(args, App::class)
}
