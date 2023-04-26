package ru.mmcs.disneywiki.repositories

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.mmcs.disneywiki.entities.DisneyCharacter
import ru.mmcs.disneywiki.entities.DisneyQueryResult
import ru.mmcs.disneywiki.services.DisneyApiService
import ru.mmcs.disneywiki.viemodels.CharacterListViewModel

class CharacterRepository(val viewModel: CharacterListViewModel) {
    val apiService: DisneyApiService = DisneyApiService.create()

    fun getCharacters(page: Int = 1){
        apiService.getCharacters(page)?.enqueue(object: Callback<DisneyQueryResult>{
            override fun onResponse(
                call: Call<DisneyQueryResult>,
                response: Response<DisneyQueryResult>
            ) {
                if(response.isSuccessful){
                    viewModel.onCharacterListReceived(response.body()?.data, response.body()?.info?.totalPages)
                }else{
                    viewModel.onFetchFailed(response.message())
                }
            }

            override fun onFailure(call: Call<DisneyQueryResult>, t: Throwable) {
                viewModel.onFetchFailed(t.message ?: "Unknown error")
            }

        })
    }
}