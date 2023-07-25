package ru.melnikov.controller

import com.elbekd.bot.Bot
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.StringUtils
import ru.melnikov.service.KanjiService

@Configuration
class BotConfig {
    @Value("\${telegram.token}")
    private val botToken: String = ""

    @Value("\${telegram.botName}")
    private val botName: String = ""

    @Autowired
    var kanjiService: KanjiService? = null

    var repeatLevel = mapOf(
        1 to 86400000, //1
        2 to 604800000, //7
        3 to 1382400000, //16
        4 to 3024000000 //35
    ) as HashMap<Int, Long>

    @Bean
    fun bot(): Bot {
        val token = botToken
        val bot = com.elbekd.bot.Bot.createPolling(token)
        bot.onCommand("/start") { msg ->
            kanjiService?.saveNewUser(msg.first.chat.id)
            bot.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(msg.first.chat.id), "Привет")
        }
        bot.onMessage {
            var userId = it.chat.id
            var user = kanjiService?.findUser(userId)
            if (user?.currentKanji != null) {
                var currentKanji = user.currentKanji
                if (StringUtils.hasText(it.text) && it.text.equals(currentKanji?.kanjiId?.hiragana)) {
                    bot.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(userId), "Верно")
                    currentKanji?.repeatCount = -1
                    currentKanji?.repeatLevel = currentKanji?.repeatLevel?.plus(1)
                    currentKanji?.repeatTime = System.currentTimeMillis() + repeatLevel[currentKanji?.repeatLevel]!!
                } else {
                    bot.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(userId), "Нет")
                    if (currentKanji?.repeatCount?.toInt() == 3) {
                        currentKanji.repeatCount = -1
                        if(currentKanji.repeatLevel!! > 0){
                            currentKanji.repeatLevel = currentKanji.repeatLevel!!.minus(1)
                        }
                        currentKanji.repeatTime = System.currentTimeMillis() + repeatLevel[currentKanji.repeatLevel]!!
                    }
                }
                if (currentKanji?.repeatCount == null) {
                    currentKanji?.repeatCount = 0
                } else {
                    currentKanji.repeatCount = currentKanji.repeatCount?.plus(1)
                }
                if (currentKanji != null) {
                    kanjiService?.saveUserKanji(currentKanji)
                }
                user.availableForLearning = true
                kanjiService?.saveUser(user)
            }
            it.text
        }
        println("Bot starting")
        bot.start()
        return bot
    }
}