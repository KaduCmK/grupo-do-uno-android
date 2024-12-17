package io.github.kaducmk.grupodouno.core.data.model

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User

data class Usuario (
    val uid: String,
    val displayName: String,
    val email: String,
    val photoUrl: String
) {
    constructor(usuarioFirestore: FirebaseUser) : this(
        uid = usuarioFirestore.uid,
        displayName = usuarioFirestore.displayName ?: "",
        email = usuarioFirestore.email ?: "",
        photoUrl = usuarioFirestore.photoUrl.toString()
    )
}