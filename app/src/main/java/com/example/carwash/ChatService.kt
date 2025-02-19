import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

interface ChatBotService {
    @POST("v1/chat/completions")
    fun getChatResponse(@Body request: ChatRequest): Call<ChatResponse>
}

data class ChatRequest(
    val model: String = "gpt-3.5-turbo",
    val messages: List<Message>
)

data class Message(
    val role: String = "user",
    val content: String
)

// Configuration Retrofit (à placer dans une classe dédiée)
object RetrofitClient {
    val instance: ChatBotService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { 
                        level = HttpLoggingInterceptor.Level.BODY 
                    })
                    .build()
            )
            .build()
            .create(ChatBotService::class.java)
    }
}
