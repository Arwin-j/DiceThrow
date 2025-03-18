package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets.Side
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    val DIESIDE = "sidenumber"
    val Result = "RESULT"
    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var currentRoll: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null){
            throwDie()
        }
        else{
            currentRoll = savedInstanceState.getInt(Result)
            dieTextView.text = savedInstanceState.getInt(Result).toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentRoll?.let { outState.putInt(Result, it) }

    }

    fun throwDie() {
        currentRoll = (Random.nextInt(dieSides)+1)
        dieTextView.text = currentRoll.toString()
    }
    companion object{
        fun newInstance (sides: Int) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(Result, sides)
            }
        }
    }
}