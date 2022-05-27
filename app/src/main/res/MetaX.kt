
import com.google.gson.annotations.SerializedName

data class MetaX(
    @SerializedName("is_end")
    val isEnd: Boolean? = null,
    @SerializedName("pageable_count")
    val pageableCount: Int? = null,
    @SerializedName("same_name")
    val sameName: SameNameX? = null,
    @SerializedName("total_count")
    val totalCount: Int? = null
)