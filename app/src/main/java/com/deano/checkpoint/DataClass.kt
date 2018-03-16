package com.deano.checkpoint

/**
 * Created by deano on 3/4/2018.
 */
class DataClass {

    data class Log(
            val cas: String,
            val kontrola: String,
            val komentar: String,
            val uzivatel: String,
            val firma: String
    )
}