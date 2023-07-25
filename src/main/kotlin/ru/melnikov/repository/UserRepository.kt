package ru.melnikov.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.melnikov.model.Kanji
import ru.melnikov.model.User

@Repository
interface UserRepository : JpaRepository<User, Long> {}