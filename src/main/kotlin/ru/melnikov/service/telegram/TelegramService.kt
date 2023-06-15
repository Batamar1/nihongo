package ru.melnikov.service.telegram

import com.elbekd.bot.Bot
import com.elbekd.bot.feature.chain.chain
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.command
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import ru.melnikov.dto.KanjiDto
import ru.melnikov.model.Kanji

@Service
class TelegramService {
    @Autowired
    @Qualifier("bot")
    val bot:Bot?= null


    fun sendMessageToBot(string: String) {
//        var str = "string"
//        bot.onCommand("/test") { msg ->
//            str = "123"
//            bot.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(msg.first.chat.id), "Hello World!")
//
//        }
//        bot.onCommand("/test2") { msg ->
//
//            bot.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(msg.first.chat.id), str)
//        }
//        bot.chain("/start")
//        { msg -> bot.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(msg.chat.id), "Hi!") }
//            .then { msg ->
//                run {
//                    while (true) {
//                        //Thread.sleep(2000)
//                        bot.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(msg.chat.id), string)
//                    }
//                }
//            }
//            .build()
//
////        bot.chain() {
////            bot.sendMessage(com.elbekd.bot.model.ChatId.IntegerId(1231654710), "test")
////        }
//        bot.start()
//        print("dqwdq")
////        val bot = bot {
////            token = botToken
////
////            dispatch {
////                command("start") {
////                    val result = bot.sendMessage(chatId = ChatId.fromId(message.chat.id), text = "Hi there!")
////                    result.fold({
////                        // do something here with the response
////                    },{
////                        // do something with the error
////                    })
////                }
////            }
////        }
////
////        bot.startPolling()
    }

}