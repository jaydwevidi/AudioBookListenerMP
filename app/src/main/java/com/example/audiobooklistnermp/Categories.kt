package com.example.audiobooklistnermp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.audiobooklistnermp.databinding.ActivityCategoriesBinding
import com.example.audiobooklistnermp.objects.CategoryObject
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Categories : AppCompatActivity() {

    private lateinit var binding : ActivityCategoriesBinding
    var mCategoryList = mutableListOf<CategoryObject>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myRecyclerView1 = binding.CategoriesGridRV
        val myAdapter = CategoriesAdapterRV(mCategoryList)

        val uid = Firebase.auth.currentUser?.uid.toString()



        val categoriesRefDB = Firebase.database.reference
            .child("Categories")

        categoriesRefDB.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val post = dataSnapshot.value
                val categoryList = mutableListOf<CategoryObject>()
                Log.e("TAG", "onDataChange: $post")//sometimes works


                for (snaps in dataSnapshot.children){
                    Log.d("TAG", "onDataChange: ${snaps.value}")
                    val x : CategoryObject = CategoryObject(
                        title = snaps.child("title").value.toString(),
                        imageURL = snaps.child("imageURL").value.toString(),
                        id = 0
                    )
                    categoryList.add(x)
                }
                mCategoryList = categoryList
                myAdapter.dataList = categoryList

                myAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })


        myRecyclerView1.apply {
            adapter = myAdapter
            layoutManager = GridLayoutManager(
                context ,
                2
            )
        }
    }
}