package ru.melnikov.service

import ru.melnikov.dto.KanjiDto
import ru.melnikov.model.Kanji
import ru.melnikov.model.User
import ru.melnikov.model.UserKanji
import java.io.File

interface KanjiService {
    fun saveKanji(kanjiDto: KanjiDto)

    fun getAllUser() : List<User>?

    fun saveNewUser(id: Long)
    fun saveUser(user: User)

    fun saveUsers(users: List<User>)
    fun saveUserKanji(userKanji: UserKanji)


    fun findUser(id: Long) : User?

    fun getAll(): List<KanjiDto>?
    fun getRandomKanji(): Kanji?
    fun saveAfterLearning(kanji: Kanji)
}