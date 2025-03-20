package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    val dieViewModel : DieViewModel by lazy {
        ViewModelProvider(this)[DieViewModel::class.java]
    }

    private lateinit var dieFragment: DieFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dieFragment = DieFragment.newInstance()

        if(supportFragmentManager.findFragmentById(R.id.fragmentContainerView) == null)
            supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, dieFragment)
            .commit()

        findViewById<Button>(R.id.rollDiceButton).setOnClickListener {
            dieViewModel.rollDie()
        }
    }
}