package app

import controller.Controller
import controller.UserController
import io.jooby.Kooby
import io.jooby.runApp

//setting hibernation with migrations an modules
import io.jooby.hibernate.HibernateModule
import io.jooby.hibernate.TransactionalRequest
import io.jooby.hikari.HikariModule
import io.jooby.json.JacksonModule
import io.jooby.flyway.FlywayModule

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

  //declaring all controllers that will be used in project
  mvc(Controller())
  mvc(UserController(entityManager))
})

fun main(args: Array<String>) {
  runApp(args, App::class)
}
