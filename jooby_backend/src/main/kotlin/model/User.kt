package model

import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class User (
        @Id
        //@GeneratedValue
        var id: UUID = UUID.randomUUID(),

        @Column(nullable = false)
        var username : String = "",

        @Column(nullable = false)
        var password : String = "",

        @Column(nullable = false)
        var email : String = "",

        @Column(nullable = false)
        var dateOfBirth: LocalDate =  LocalDate.now(),

        @Column(nullable = false)
        var dateCreated: OffsetDateTime =  OffsetDateTime.now()
){

    @Override
    override fun equals(user: Any?): Boolean {
        when(user){
            user is User -> return Objects.equals(id, (user as User).id)
        }
        return false
    }

    @Override
    override fun hashCode(): Int {
        return Objects.hash(id)
    }

}

