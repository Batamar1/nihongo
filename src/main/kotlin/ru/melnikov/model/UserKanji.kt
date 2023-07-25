package ru.melnikov.model

import jakarta.persistence.*

@Table(name = "user_kanji")
@Entity
class UserKanji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "user_id", nullable = false)
    var userId: User? = null

    @OneToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "kanji_id", referencedColumnName = "id")
    var kanjiId: Kanji? = null

    @Column(name = "repeatTime", nullable = true)
    var repeatTime: Long? = null;

    @Column(name = "repeatCount", nullable = true)
    var repeatCount: Long? = 0;

    @Column(name = "repeatLevel", nullable = false)
    var repeatLevel: Int? = 0;
}