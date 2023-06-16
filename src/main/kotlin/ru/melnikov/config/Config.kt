package ru.melnikov.config

import com.elbekd.bot.Bot
import com.elbekd.bot.feature.chain.chain
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import ru.melnikov.service.KanjiService
import ru.melnikov.service.telegram.TelegramService

@Configuration
@EnableScheduling
class Config {


    @Autowired
    @Qualifier("bot")
    val bot:Bot?= null

    @Value("\${telegram.idUser}")
    private val idUser: Long = 0

    @Autowired
    var kanjiService: KanjiService? = null

    var repeatCount = 0

    @Scheduled(fixedRate = 60000)
    fun fixedRateScheduledTask() {
        print("test")

        runBlocking {
            learning()
        }

    }

    suspend fun learning(){
        if(repeatCount == 4) return
        repeatCount++
        val kanji = kanjiService?.getRandomKanji()
        bot?.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(idUser), kanji?.value ?: "")
        bot?.onMessage {
            if (it.text != null && kanji != null && kanji.hiragana != null &&
                kanji.hiragana.equals(it.text)
            ) {
                bot?.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(idUser), "Верно")
                kanjiService?.saveAfterLearning(kanji)
            } else {
                bot?.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(idUser), "Нет")
            }
            learning()
        }
    }
}