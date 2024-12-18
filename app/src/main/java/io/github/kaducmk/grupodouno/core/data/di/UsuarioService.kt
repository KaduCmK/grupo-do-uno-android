package io.github.kaducmk.grupodouno.core.data.di

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import io.github.kaducmk.grupodouno.core.data.model.Usuario
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsuarioService @Inject constructor() {
    private val db = FirebaseFirestore.getInstance()

    suspend fun setUsuario(usuario: Usuario) {
        try {
            val doc = db.collection("users")
                .document(usuario.uid!!)
                .get()
                .await()

            if (!doc.exists())
                doc.reference.set(usuario).await()

            Log.d("UsuariosService", "Usuario set")
        }
        catch (e: Exception) {
            Log.e("UsuariosService", "Error setting usuario", e)
        }
    }

    suspend fun findUsuario(str: String): List<Usuario> {
        return try {
            val usuarios = db.collection("users")
                .whereGreaterThanOrEqualTo("displayName", str)
                .whereLessThanOrEqualTo("displayName", str + "\uf7ff")
                .get()
                .await()

            usuarios.mapNotNull { user ->
                user.toObject(Usuario::class.java)
            }
        }
        catch (e: Exception) {
            Log.e("UsuariosService", "Error getting usuarios", e)
            emptyList()
        }
    }
}