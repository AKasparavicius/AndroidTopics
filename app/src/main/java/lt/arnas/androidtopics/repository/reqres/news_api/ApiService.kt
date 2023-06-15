package lt.arnas.androidtopics.repository.reqres.news_api


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getTopNews(
        @Query("country") country: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = "f31a7b84325d4497b1e99126a3039f0a"
    ): Response<TopHeadlinesResponse>
}
