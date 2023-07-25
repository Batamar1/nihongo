package ru.melnikov.service.impl

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.melnikov.dto.KanjiDto
import ru.melnikov.model.Kanji
import ru.melnikov.model.User
import ru.melnikov.model.UserKanji
import ru.melnikov.repository.KanjiRepository
import ru.melnikov.repository.UserKanjiRepository
import ru.melnikov.repository.UserRepository
import ru.melnikov.service.KanjiService
import java.io.File
import kotlin.system.measureTimeMillis

@Service
class KanjiServiceImpl : KanjiService {
    var repeatLevel = mapOf(1 to 86400000, //1
        2 to 604800000, //7
        3 to 1382400000, //16
        4 to 3024000000) as HashMap<Int, Long> //35

    @Autowired
    var kanjiRepository: KanjiRepository? = null

    @Autowired
    var userKanjiRepository: UserKanjiRepository? = null

    @Autowired
    var userRepository: UserRepository? = null

    override fun saveKanji(kanjiDto: KanjiDto) {
        kanjiRepository?.save(kanjiDtoToKanji(kanjiDto));

    }

    override fun getAllUser(): List<User>? {
        return userRepository?.findAll()
    }

    @Transactional
    override fun saveUser(user: User) {
        userRepository?.save(user)
    }

    override fun saveUsers(users: List<User>) {
        userRepository?.saveAll(users)
    }

    override fun saveUserKanji(userKanji: UserKanji) {
        userKanjiRepository?.save(userKanji)
    }

    override fun saveNewUser(id: Long) {
        var user = User()
        user.id = id
        user.currentKanji = null
        user.repeatTimeToday =0
        saveUser(user)
        var kanjiList = kanjiRepository?.findAll()

        var savedUser = findUser(id)
        var saveUserList = ArrayList<UserKanji>()
        kanjiList?.forEach {
            var userKanji = UserKanji()
            userKanji.userId = savedUser
            userKanji.kanjiId = it
            userKanji.repeatTime = 0
            userKanji.repeatLevel = 0
            userKanji.repeatCount = 0
            saveUserList.add(userKanji)
        }
        userKanjiRepository?.saveAll(saveUserList)
    }

    override fun findUser(id: Long) : User?{
        return userRepository?.findById(id)?.orElse(null)
    }

    override fun getAll(): List<KanjiDto>? {
        var kanjiList = kanjiRepository?.findAll();
        return kanjiList?.map {kanjiToKanjiDto(it)}
    }

    override fun getRandomKanji(): Kanji? {
//        var currentTime = System.currentTimeMillis()
//        var kanji = kanjiRepository?.findAll()
//            ?.filter { (it.repeatTime ?: 0) < currentTime }
//            ?.random()
//        kanji?.repeatLevel?.plus(1)
//       // kanji?.
//        //return kanji?.value?.let { getFile(it) }
//        return kanji
        return null
    }

    override fun saveAfterLearning(kanji: Kanji) {

//        if(kanji.repeatCount == 3) {
//            kanji.repeatCount = 0
//            kanji.repeatLevel = kanji.repeatLevel?.plus(1)
//            kanji.repeatTime = System.currentTimeMillis().plus(repeatLevel.get(kanji.repeatLevel) ?: 0)
//        }else{
//            kanji.repeatCount = kanji.repeatCount.plus(1)
//        }
//        kanjiRepository?.save(kanji)
    }

    fun kanjiDtoToKanji(kanjiDto: KanjiDto) : Kanji{
        val kanji = Kanji()
        kanji.hiragana = kanjiDto.hiragana
        kanji.value = kanjiDto.value
     //   kanji.kanjiImgName = kanjiDto.kanjiImgName
        return kanji
    }

    fun kanjiToKanjiDto(kanji: Kanji) : KanjiDto{
        val kanjiDto = KanjiDto()
        kanjiDto.id = kanji.id
        kanjiDto.hiragana = kanji.hiragana
        kanjiDto.value = kanji.value
//        kanjiDto.repeatLevel = kanji.repeatLevel
//        kanjiDto.repeatTime = kanji.repeatTime
       // kanjiDto.kanjiImgName = kanji.kanjiImgName
        return kanjiDto
    }

    fun getFile(fileName: String) : File {
        return File("images/${fileName.plus(".jpg")}")
    }
}