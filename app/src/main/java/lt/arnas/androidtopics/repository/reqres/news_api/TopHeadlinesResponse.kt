package lt.arnas.androidtopics.repository.reqres.news_api

data class TopHeadlinesResponse(
    val status: String = "",
    val totalResults: Int = -1,
    val articles: MutableList<Article> = mutableListOf()
)
