package ru.melnikov.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.melnikov.model.Kanji

@Repository
interface KanjiRepository : JpaRepository<Kanji, Long> {
}