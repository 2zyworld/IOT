
import com.google.gson.annotations.SerializedName

data class AskHelpData(
    @SerializedName("documents")
    val documents: List<Any>? = null,
    @SerializedName("meta")
    val meta: Meta? = null
)