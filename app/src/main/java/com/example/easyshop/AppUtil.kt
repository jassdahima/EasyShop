package com.example.easyshop

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.compose.ui.layout.FirstBaseline
import androidx.core.content.edit
import com.example.easyshop.model.OrderModel
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.google.type.DateTime
import com.razorpay.Checkout
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID

object AppUtil {


    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()

    }


    fun addToCart(productId: String, context: Context) {

        val userDoc = Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)

        userDoc.get().addOnCompleteListener {
            if (it.isSuccessful) {
                val currentCart = it.result.get("cartItems") as? Map<String, Long> ?: emptyMap()
                val currentQuantity = currentCart[productId] ?: 0
                val updatedQuantity = currentQuantity + 1;

                val updatedCart = mapOf("cartItems.$productId" to updatedQuantity)

                userDoc.update(updatedCart)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showToast(context, "Item added to the cart")
                        } else {
                            showToast(context, "Failed adding to the cart")
                        }
                    }
            }
        }
    }


    fun removeFromCart(productId: String, context: Context, removeAll: Boolean = false) {

        val userDoc = Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)

        userDoc.get().addOnCompleteListener {
            if (it.isSuccessful) {
                val currentCart = it.result.get("cartItems") as? Map<String, Long> ?: emptyMap()
                val currentQuantity = currentCart[productId] ?: 0
                val updatedQuantity = currentQuantity - 1;


                val updatedCart = if (updatedQuantity <= 0 || removeAll)
                    mapOf("cartItems.$productId" to FieldValue.delete())
                else
                    mapOf("cartItems.$productId" to updatedQuantity)



                userDoc.update(updatedCart)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showToast(context, "Item removed to the cart")
                        } else {
                            showToast(context, "Failed removing item to the cart")
                        }
                    }
            }
        }
    }


    fun clearCarAndAddToOrders() {
        val userDoc = Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)


        userDoc.get().addOnCompleteListener {
            if (it.isSuccessful) {
                val currentCart = it.result.get("cartItems") as? Map<String, Long> ?: emptyMap()

                val order = OrderModel(
                    id = "ORD_" + UUID.randomUUID().toString().replace("-", "").take(10)
                        .uppercase(),
                    userId = FirebaseAuth.getInstance().currentUser?.uid!!,
                    date = Timestamp.now(),
                    items = currentCart,
                    status = "ORDERED",
                    address = it.result.get("address") as String
                )

                Firebase.firestore.collection("orders")
                    .document(order.id).set(order)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            userDoc.update("cartItems", FieldValue.delete())
                        }
                    }


            }
        }


    }


    fun getDiscountPercentage(): Float {
        return 10.0f
    }

    fun getTaxPercentage(): Float {
        return 13.0f
    }

    fun razorpayApiKey(): String {
        return ""
    }


    fun startPayment(amount: Float) {
        val checkout = Checkout()
        checkout.setKeyID(razorpayApiKey())


        val options = JSONObject()
        options.put("name", "Easy Shop")
        options.put("description", "")
        options.put("amount", amount * 100)
        options.put("currency", "INR")

        checkout.open(GlobalNavigation.navController.context as Activity, options)
    }


    fun formatDate(timestamp: Timestamp): String {
        val sdf = SimpleDateFormat("dd,MMM,yyyy,hh:mm a", Locale.getDefault())
        return sdf.format(timestamp.toDate().time)
    }


    private const val PREF_NAME = "favorite_pref"
    private const val KEY_FAVORITES = "favorites_list"

    fun addOrRemoveFromFavorite(context: Context,productId : String) {
        val list = getFavoriteList(context).toMutableSet()
        if (list.contains(productId)){
            list.remove(productId)
            showToast(context,"Item removed from Favorite")
        }else{
            list.add(productId)
            showToast(context,"Item added to favorite")
        }
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        prefs.edit {
            putStringSet(KEY_FAVORITES,list)
        }

    }


    fun checkFavorite(context: Context,productId : String) : Boolean{
        if (getFavoriteList(context).contains(productId)){
            return true
        }
        return false;


    }


    fun getFavoriteList(context: Context): Set<String> {

        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet(KEY_FAVORITES,emptySet()) ?: emptySet()

    }









}




































