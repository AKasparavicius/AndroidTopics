package lt.arnas.androidtopics.repository.reqres

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("data")
    var userList: MutableList<User> = mutableListOf()
)
