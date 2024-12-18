package io.github.kaducmk.grupodouno.core.data.di

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import io.github.kaducmk.grupodouno.core.data.model.Usuario
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject

class AuthService @Inject constructor (
    private val usuarioService: UsuarioService
) {
    suspend fun googleSignIn(context: Context): Result<AuthResult> {
        val credentialManager = CredentialManager.create(context)
        val firebaseAuth = FirebaseAuth.getInstance()

        return try {

                val ranNonce = UUID.randomUUID().toString()
                val bytes = ranNonce.toByteArray()
                val md = MessageDigest.getInstance("SHA-256")
                val digest = md.digest(bytes)
                val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it) }

                val googleIdOption = GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId("1002756454440-4ddampgnttprs15cjqjtmo279f4vnrmq.apps.googleusercontent.com")
                    .setNonce(hashedNonce)
                    .build()

                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                val result = credentialManager.getCredential(context, request)
                val credential = result.credential

                if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(credential.data)
                    val authCredential =
                        GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                    val authResult = firebaseAuth.signInWithCredential(authCredential).await()

                    usuarioService.setUsuario(Usuario(authResult.user!!, emptyList()))

                    Result.success(authResult)
                } else throw RuntimeException("Received an invalid credential type")
            } catch (e: GetCredentialCancellationException) {
                Log.e("AuthService", e.toString())
                Result.failure(Exception("Sign-in was cancelled."))
            } catch (e: Exception) {
                Log.e("AuthService", "Error signing in", e)
                Result.failure(e)
            }
    }

    fun signOut() = FirebaseAuth.getInstance().signOut()

    fun getSignedInUser() = FirebaseAuth.getInstance().currentUser
}