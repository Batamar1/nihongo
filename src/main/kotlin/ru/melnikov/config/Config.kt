package ru.melnikov.config

import com.elbekd.bot.Bot
import com.elbekd.bot.feature.chain.chain
import com.elbekd.bot.model.toChatId
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import ru.melnikov.model.UserKanji
import ru.melnikov.service.KanjiService
import ru.melnikov.service.telegram.TelegramService
import java.util.concurrent.TimeUnit

@Configuration
@EnableScheduling
class Config {


    @Autowired
    @Qualifier("bot")
    val bot: Bot? = null

    @Value("\${telegram.idUser}")
    private val idUser: Long = 0

    @Autowired
    var kanjiService: KanjiService? = null

    var repeatCount = 0

    @Scheduled(fixedRate = 10000)
    fun fixedRateScheduledTask() {
        learning()

    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    fun updateUserInfo() {
        var users = kanjiService?.getAllUser()
        users?.stream()?.filter{
            !it.availableForLearning
        }?.forEach{ user ->
            if (user.userKanjiSet != null) {
                var currentTime = System.currentTimeMillis()
                var kanjiList = user.userKanjiSet
                    .filter { (it.repeatTime ?: 0) < currentTime }
                if(kanjiList.isNotEmpty()) {
                    user.availableForLearning = true
                    kanjiService?.saveUser(user)
                }
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    fun updateRepeatCountForDay() {
        var users = kanjiService?.getAllUser()
        users?.stream()?.forEach{ user ->
           user.repeatTimeToday = 0
        }
        if (users != null) {
            kanjiService?.saveUsers(users)
        }
    }

    fun learning() {
        var users = kanjiService?.getAllUser()
        users?.stream()?.filter {
            it.availableForLearning
        }?.forEach { user ->
            if (user.userKanjiSet != null) {
                var currentTime = System.currentTimeMillis()
                var kanjiList = user.userKanjiSet
                    .filter { (it.repeatTime ?: 0) < currentTime }
                if(kanjiList.isEmpty()){
                    runBlocking {
                        bot?.sendMessage(user.id.toChatId(), "Пока все")
                    }
                    user.availableForLearning = false
                    kanjiService?.saveUser(user)
                }else {
                    var randomKanji = kanjiList.random()
                    runBlocking {
                        randomKanji.kanjiId?.value?.let { bot?.sendMessage(user.id.toChatId(), it) }
                    }
                    user.repeatTimeToday = user.repeatTimeToday.plus(1)
                    user.availableForLearning = false
                    user.currentKanji = randomKanji
                    kanjiService?.saveUser(user)
                }
            }
        }
    }
}