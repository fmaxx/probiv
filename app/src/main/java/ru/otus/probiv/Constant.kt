package ru.otus.probiv

/**
 * Created by Maxim Firsov on 2019-11-17.
 * PowerDot
 * firsoffmaxim@gmail.com
 */
class Constant {
    companion object {
        val FSS_API_KEY
            get() = BuildConfig.FSS_API_KEY

        val FSS_API_END_POINT
            get() = BuildConfig.FSS_API_END_POINT
    }
}