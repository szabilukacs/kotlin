
import com.zoltanlorinczi.project_retrofit.api.BackendConstants
import com.zoltanlorinczi.project_retrofit.api.MarketPlaceApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit instance for network access.
 *
 * Author:  Zoltan Lorinczi
 * Date:    11/8/2021
 */
object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BackendConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Will not be initialized unless you use it!
     * It is initialized only once. Next time when you use it, you get the value from cache memory.
     */
    val marketPlaceApiService: MarketPlaceApiService by lazy {
        retrofit.create(MarketPlaceApiService::class.java)
    }
}