package ru.otus.probiv.api.provider.fss

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.otus.probiv.api.provider.fss.data.FssResponse
import ru.otus.probiv.api.provider.fss.data.FssResultResponse
import ru.otus.probiv.api.provider.fss.data.FssStatusResponse

/**
 * Created by Maxim Firsov on 2019-11-17.
 * firsoffmaxim@gmail.com
 *
 * Документация по всему сервису: https://API-ip.fssprus.ru/about
 */

interface FssApi {

    /**
     * запуск поиска по базе физического лица
     *
     * @param region Код региона отдела судебных приставов в соответствии со справочником регионов ФССП России
     * @param firstname Имя должника по исполнительному производству
     * @param lastname Фамилия должника по исполнительному производству
     * @param birthdate Дата рождения должника в формате «дд.мм.гггг»
     * @param secondname Отчество должника по исполнительному производству
     *
     * @return стандартный объект с id задачи
     * */
    @GET("api/v1.0/search/physical")
    suspend fun startSearchPhysical(
        @Query("region") region: Int,
        @Query("firstname") firstname: String,
        @Query("lastname") lastname: String,
        @Query("birthdate") birthdate: String?,
        @Query("secondname") secondname: String?
    ): Response<FssResponse>


    /**
     * запуск поиска по базе юридических лиц
     * @param region Код региона отдела судебных приставов в соответствии со справочником регионов ФССП России
     * @param name Имя должника по исполнительному производству
     *
     * @return стандартный объект с id задачи
     * */
    @GET("api/v1.0/search/legal")
    suspend fun startSearchLegal(
        @Query("region") region: Int,
        @Query("name") name: String
    ): Response<FssResponse>


    /**
     * Запрос на состояние задачи
     * @param task Идентификатор задачи
     * @return Ответом на данный запрос будет выдан массив, в котором содержится статус выполняемой задачи:
    ‘status’ : 'integer’, где статус может иметь следующие значения:
    0 — обработка завершена, с помощью метода /result можно получить результаты;
    1 — обработка начата, с помощью метода /result можно получить частичные результаты группового запроса;
    2 — обработка не начиналась, запрос находится в очереди;
    3 — обработка завершена, имели место ошибки, с помощью метода /result можно получить частичные результаты;
    ‘progress’ : 'string’, Количество выполненных задач из общего количества задач.
     *
     * */
    @GET("api/v1.0/status/")
    suspend fun getTaskStatus(@Query("task") task: String): Response<FssStatusResponse>


    /**
     * Запрос состояния задачи.
     *
     * @param task Идентификатор задачи
     *
     * @return Ответом на данный запрос будет выдан массив, в котором содержится результат выполняемой задачи:
    ‘status’ : 'integer’, где статус может иметь следующие значения:
    0 - Задача выполнена без ошибок;
    1 - Задача находится в обработке, результаты группового запроса не являются полными;
    2 - Задача находится в очереди на выполнение;
    3 - Задача выполнена, имеются ошибки;
    ‘query’ : 'array’, массив, элементы которого содержат значения запросов.
    ‘result’ : 'array’, массив, элементы которого содержат ответы на запросы.
     *
     * */
    @GET("api/v1.0/result/")
    suspend fun getTaskResult(@Query("task") task: String): Response<FssResultResponse>
}