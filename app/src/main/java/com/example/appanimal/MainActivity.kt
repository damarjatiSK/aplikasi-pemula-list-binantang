package com.example.appanimal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAnimals: RecyclerView
    private val list = ArrayList<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvAnimals = findViewById(R.id.rv_animals)
        rvAnimals.setHasFixedSize(true)
        list.addAll(getListAnimals())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val aboutItem = menu?.findItem(R.id.about_page)
        val sharetItem = menu?.findItem(R.id.action_share)
        if (aboutItem != null) {
            sharetItem?.isVisible = false
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getListAnimals(): ArrayList<Animal> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataCategory = resources.getStringArray(R.array.data_category)
        val dataFood = resources.getStringArray(R.array.data_food)
        val dataHeavy = resources.getStringArray(R.array.data_heavy)
        val dataAreaDistribution = resources.getStringArray(R.array.data_area_distribution)
        val listAnimal = ArrayList<Animal>()
        for (i in dataName.indices) {
            val animal = Animal(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataCategory[i], dataFood[i], dataHeavy[i], dataAreaDistribution[i])
            listAnimal.add(animal)
        }
        return listAnimal
    }

    private fun showRecyclerList() {
        rvAnimals.layoutManager = LinearLayoutManager(this)
        val listAnimalAdapter = ListAnimalAdapter(list)
        rvAnimals.adapter = listAnimalAdapter

        listAnimalAdapter.setOnItemClickCallback(object : ListAnimalAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Animal) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(hero: Animal) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
}