package ru.melnikov.model

import jakarta.persistence.*

@Table(name = "kanji")
@Entity
class Kanji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null


    @Column(name = "value", nullable = false)
    var value: String? = null

    @Column(name = "repeatCount", nullable = false)
    var repeatCount : Int = 0;

    @Column(name = "hiragana", nullable = false)
    var hiragana: String? = null;

    @Column(name = "repeatTime", nullable = true)
    var repeatTime: Long? = null;

    @Column(name = "repeatLevel", nullable = false)
    var repeatLevel: Int? = 0;

//    @Column(name = "kanji_img_name" ,nullable = false)
//    var kanjiImgName: String? = null;
}