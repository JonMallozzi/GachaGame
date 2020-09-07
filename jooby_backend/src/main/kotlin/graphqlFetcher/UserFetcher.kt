package graphqlFetcher

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import model.User
import java.time.LocalDate
import java.util.*
import io.jooby.Context
import io.jooby.require
import javax.persistence.EntityManager

//Class containing all the User Graphql Resolvers
class UserFetcher() {

    //GET Resolver for getting all users
    fun getAllUsers(): DataFetcher<List<User>>? {
        return DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val ctx: Context = dataFetchingEnvironment.getContext()
            ctx.require(EntityManager::class).createQuery("from User", User::class.java).getResultList()
        }
    }

    //GET Resolver for a single user based on the provided id
    fun getUserById(): DataFetcher<User>? {
        return DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val userId = dataFetchingEnvironment.getArgument<String>("id")
            val ctx: Context = dataFetchingEnvironment.getContext()
            ctx.require(EntityManager::class).find(User::class.java, UUID.fromString(userId))
        }
    }

    //GET Resolver for a single user based on the provided username
    fun getUserByUsername(): DataFetcher<User> {
        return DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val username = dataFetchingEnvironment.getArgument<String>("username")
            println(username)
            val ctx: Context = dataFetchingEnvironment.getContext()
            ctx.require(EntityManager::class)
                    .createQuery("SELECT u FROM User u WHERE u.username = :username", User::class.java)
                        .setParameter("username",username).resultList.first()
        }
    }

    //POST Resolver for creating a new user
    fun createUser(): DataFetcher<User> {
        return DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val user = User(
                    UUID.randomUUID(),
                    dataFetchingEnvironment.getArgument<String>("username"),
                    dataFetchingEnvironment.getArgument<String>("password"),
                    dataFetchingEnvironment.getArgument<String>("email"),
                    dataFetchingEnvironment.getArgument<LocalDate>("dateOfBirth")
                )
            val ctx: Context = dataFetchingEnvironment.getContext()
            ctx.require(EntityManager::class).persist(user)
            user
        }
    }

    //UPDATE Resolver for updating a given user
    fun updateUser(): DataFetcher<User> {
        return DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val userId = dataFetchingEnvironment.getArgument<String>("id")
            val ctx: Context = dataFetchingEnvironment.getContext()
            val userToBeUpdated: User = ctx.require(EntityManager::class).find(User::class.java, UUID.fromString(userId))

            userToBeUpdated.username = dataFetchingEnvironment.getArgument<String>("username") ?: userToBeUpdated.username

            userToBeUpdated.password = dataFetchingEnvironment.getArgument<String>("password") ?: userToBeUpdated.password

            userToBeUpdated.email = dataFetchingEnvironment.getArgument<String>("email") ?: userToBeUpdated.email

            userToBeUpdated.dateOfBirth = dataFetchingEnvironment.getArgument<LocalDate>("dateOfBirth") ?: userToBeUpdated.dateOfBirth

            ctx.require(EntityManager::class).merge(userToBeUpdated)
            userToBeUpdated
        }
    }

    //DELETE Resolver for deleting a user by the given id
    fun deleteUser(): DataFetcher<String> {
        return DataFetcher { dataFetchingEnvironment: DataFetchingEnvironment ->
            val userId = dataFetchingEnvironment.getArgument<String>("id")
            val ctx: Context = dataFetchingEnvironment.getContext()
            val userToBeDeleted: User = ctx.require(EntityManager::class).find(User::class.java, UUID.fromString(userId))
            ctx.require(EntityManager::class).remove(userToBeDeleted)
             "User with the id: $userId has been successfully deleted"
        }
    }
}