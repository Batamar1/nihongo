package ru.melnikov.dto

import jakarta.persistence.*
import java.io.File

class KanjiDto {
    var id: Long? = null

    var value: String? = null

    var hiragana: String? = null;

    var repeatTime: Long? = null;

    var repeatLevel: Int? = null;

//    var kanjiImgName: String? = null;
}