package ru.melnikov.model

import jakarta.persistence.*

@Table(name = "kanji")
@Entity
class Kanji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0

    @Column(name = "value", nullable = false)
    var value: String? = null

    @Column(name = "hiragana", nullable = false)
    var hiragana: String? = null;

//    @Column(name = "kanji_img_name" ,nullable = false)
//    var kanjiImgName: String? = null;
}