package ru.otus.probiv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.otus.probiv.api.RepositoryResult
import ru.otus.probiv.api.provider.fss.FssRepository
import ru.otus.probiv.api.provider.fss.data.FssStartSearchRequest
import timber.log.Timber

class MainActivity : AppCompatActivity() {


    private val repository = FssRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // test call
        CoroutineScope(Dispatchers.IO).launch {
            val request = FssStartSearchRequest(
                74,
                "Иван",
                "Иванов",
                null,
                null
            )
            val result = repository.startSearchPhysical(request)

            withContext(Dispatchers.Main) {
                when (result) {
                    is RepositoryResult.Success -> {
                        Timber.d("~~ startSearchPhysical: ${result.value}")
                        result.value.response?.let {
                            getStatus(it.task)
                        }
//                        getStatus(result.value.response.task)
                    }
                    is RepositoryResult.Error -> {
                        Timber.d("~~ startSearchPhysical: ${result.error}")
                    }
                }
            }
        }
    }

    private fun getStatus(taskId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.getTaskStatus(taskId)
            withContext(Dispatchers.Main) {
                when (result) {
                    is RepositoryResult.Success -> {
                        Timber.d("~~ getTaskStatus: ${result.value}")
                    }
                    is RepositoryResult.Error -> {
                        Timber.d("~~ getTaskStatus: ${result.error}")
                    }
                }
            }
        }
    }
}
