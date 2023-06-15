package ru.melnikov.service

import ru.melnikov.dto.KanjiDto
import ru.melnikov.model.Kanji
import java.io.File

interface KanjiService {
    fun save(kanjiDto: KanjiDto)
    fun getAll(): List<KanjiDto>?
    fun getRandomKanji(): Kanji?
    fun saveAfterLearning(kanji: Kanji)
}