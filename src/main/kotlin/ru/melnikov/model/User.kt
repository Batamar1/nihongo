package ru.melnikov.model

import jakarta.persistence.*

@Table(name = "user_nihongo")
@Entity
class User {
    @Id
    @Column(name = "id", nullable = false)
    var id: Long = 0

    @OneToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "user_kanji_id", referencedColumnName = "id")
    var currentKanji : UserKanji? = null;

    @Column(name = "repeat_time_today", nullable = false)
    var repeatTimeToday: Int = 0;

    @Column(name = "available_for_learning", nullable = false)
    var availableForLearning: Boolean = true;


    @OneToMany(mappedBy = "userId", fetch=FetchType.EAGER)
    val userKanjiSet: List<UserKanji>? = null
}