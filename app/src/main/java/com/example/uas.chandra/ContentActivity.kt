package com.example.chandra

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chandra.databinding.ActivityContentBinding
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class ContentActivity : AppCompatActivity() {
    private lateinit var WisataRecyclerView: RecyclerView
    private lateinit var Wisatalist:MutableList<Wisata>
    private lateinit var WisataAdapter: WisataAdapter
    private lateinit var binding: ActivityContentBinding
    private var mStorage: FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.chandra.R.layout.activity_content)
        binding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

       WisataRecyclerView= findViewById(R.id.RecyclerView)
        WisataRecyclerView.setHasFixedSize(true)
        WisataRecyclerView.layoutManager = LinearLayoutManager(this@ContentActivity)
        binding.myDataLoaderProgressBar
        Wisatalist = ArrayList()
        WisataAdapter = WisataAdapter(this@ContentActivity, Wisatalist)
        WisataRecyclerView.adapter = WisataAdapter

        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("movie")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ContentActivity, error.message, Toast.LENGTH_SHORT).show()
                binding.myDataLoaderProgressBar.visibility = View.GONE
            }
        })
    }
}