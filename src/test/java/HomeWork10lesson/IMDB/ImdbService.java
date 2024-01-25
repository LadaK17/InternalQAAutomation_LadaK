package HomeWork10lesson.IMDB;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ImdbService {

  @GET("chart/top")
  @Headers({"user-agent: Mozilla/5.0", "accept-language: en-US,en;q=0.9"})
  Call<ResponseBody> getTopChart();
}
