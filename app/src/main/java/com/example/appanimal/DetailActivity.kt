package com.example.appanimal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Mendapatkan data animal dari intent
        val dataAnimal = intent.getParcelableExtra<Animal>("key_animal")

        // Menampilkan gambar animal
        val imgAnimal: ImageView = findViewById(R.id.img_animal)
        if (dataAnimal != null) {
            imgAnimal.setImageResource(dataAnimal.photo)
        }

        val tvName: TextView = findViewById(R.id.tv_name)
        if (dataAnimal != null) {
            tvName.text = dataAnimal.name
        }

        val tvDescription: TextView = findViewById(R.id.tv_description)
        if (dataAnimal != null) {
            tvDescription.text = dataAnimal.description
        }

        val tvCategory: TextView = findViewById(R.id.tv_category)
        if (dataAnimal != null) {
            tvCategory.text = dataAnimal.category
        }

        val tvFood: TextView = findViewById(R.id.tv_food)
        if (dataAnimal != null) {
            tvFood.text = dataAnimal.food
        }

        val tvHeavy: TextView = findViewById(R.id.tv_heavy)
        if (dataAnimal != null) {
            tvHeavy.text = dataAnimal.heavy
        }

        val tvAreaDistribution: TextView = findViewById(R.id.tv_area_distribution)
        if (dataAnimal != null) {
            tvAreaDistribution.text = dataAnimal.areaDistribution
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val aboutItem = menu?.findItem(R.id.about_page)
        if (aboutItem != null) {
            aboutItem?.isVisible = false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.action_share -> {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, "Bagikan")
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, "Bagikan melalui :"))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}