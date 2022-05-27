
import com.google.gson.annotations.SerializedName

data class AskHelpDataX(
    @SerializedName("documents")
    val documents: List<Any>? = null,
    @SerializedName("meta")
    val meta: MetaX? = null
)