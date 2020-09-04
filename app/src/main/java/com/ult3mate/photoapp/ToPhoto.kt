package com.ult3mate.photoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_to_photo.*

class ToPhoto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_photo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val url = intent.getStringExtra("url")
        val title = intent.getStringExtra("title")
        supportActionBar?.title = title
            Picasso
            .get()
            .load(url)
            .into(image_p)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}