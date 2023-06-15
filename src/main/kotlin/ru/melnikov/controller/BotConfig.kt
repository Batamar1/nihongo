package ru.melnikov.controller

import com.elbekd.bot.Bot
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BotConfig {
    @Value("\${telegram.token}")
    private val botToken: String = ""

    @Value("\${telegram.botName}")
    private val botName: String = ""

    @Bean
    fun bot() : Bot {
        val token = botToken
        val bot1 = com.elbekd.bot.Bot.createPolling(token)
        bot1.onCommand("/test") { msg ->
            bot1.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(msg.first.chat.id), "Hello World!")

        }
        bot1.start()
        return bot1
    }
}