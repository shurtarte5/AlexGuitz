package com.hurtarte.alexguitz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hurtarte.alexguitz.fragments.ContactFragment
import com.hurtarte.alexguitz.fragments.GitHubFragment
import com.hurtarte.alexguitz.fragments.MyProfileFragment
import kotlinx.android.synthetic.main.activity_container.*

class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        var myFragment: Fragment?=null

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,MyProfileFragment()).commit()

        bottomNavigationView.setOnNavigationItemSelectedListener(this.navListener)

    }

    private val navListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.getItemId()) {
                R.id.alex -> selectedFragment = MyProfileFragment()
                R.id.contact -> selectedFragment = ContactFragment()
                R.id.repos -> selectedFragment = GitHubFragment()
            }
            if (selectedFragment != null) {
                supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    selectedFragment
                ).commit()
            }
            true
        }


}
