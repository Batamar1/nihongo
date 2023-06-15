package ru.melnikov.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.melnikov.dto.KanjiDto
import ru.melnikov.service.KanjiService
import ru.melnikov.service.telegram.TelegramService
import java.io.File

@RestController
@RequestMapping(value = ["/api/v1/kanji"])
class KanjiController {

    @Autowired
    var kanjiService: KanjiService? = null

    @Autowired
    var telegramService: TelegramService? = null

    @PostMapping("/save")
    fun saveKanji(@RequestBody kanjiDto: KanjiDto) : String{
        kanjiService?.save(kanjiDto)
        return "Success"
    }

    @GetMapping("/")
    fun getKanji(): List<KanjiDto> {
        return kanjiService?.getAll()?: ArrayList()
    }

//    @GetMapping(value = ["/img"],  produces = [MediaType.IMAGE_JPEG_VALUE])
//    @ResponseBody
//    fun getImg(): ByteArray? {
//        var file = kanjiService?.getRandomKanji()
//        return file?.readBytes()
//    }

    @PostMapping("/send_bot")
    fun sendBot(@RequestBody kanjiDto: KanjiDto) : String{
        val hiragana = kanjiDto.hiragana
        if(hiragana != null) {
            telegramService?.sendMessageToBot(hiragana)
        }
        return "Success"
    }

}