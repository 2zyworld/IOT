
import com.google.gson.annotations.SerializedName

data class SameName(
    @SerializedName("keyword")
    val keyword: String? = null,
    @SerializedName("region")
    val region: List<Any>? = null,
    @SerializedName("selected_region")
    val selectedRegion: String? = null
)