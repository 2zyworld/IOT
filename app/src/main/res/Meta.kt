
import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("is_end")
    val isEnd: Boolean? = null,
    @SerializedName("pageable_count")
    val pageableCount: Int? = null,
    @SerializedName("same_name")
    val sameName: SameName? = null,
    @SerializedName("total_count")
    val totalCount: Int? = null
)