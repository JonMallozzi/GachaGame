package controller

import io.jooby.annotations.GET
import io.jooby.annotations.Path
import model.User


import javax.persistence.EntityManager


class UserController(em: EntityManager) {

    private val entityManager: EntityManager = em

    @Path("/user")
    @GET
    fun getAllUsers(): Any {
        return entityManager.createQuery("from User", User::class.java).getResultList()
    }
}
