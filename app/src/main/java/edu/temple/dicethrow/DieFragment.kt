package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets.Side
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.callbackFlow
import kotlin.random.Random

class DieFragment : Fragment() {

    val DIESIDE = "sidenumber"
    val PREVIOUS_ROLL = "previous_roll"
    lateinit var dieTextView: TextView

    var dieSides: Int = 6
    var currentRoll: Int = 0

    lateinit var dieViewModel: DieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }

        dieViewModel = ViewModelProvider(requireActivity())[DieViewModel::class.java]
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

        dieViewModel.getCurrentRoll().observe(viewLifecycleOwner){
            dieTextView.text = it.toString()
        }

        if(dieViewModel.getCurrentRoll().value == null)
            dieViewModel.rollDie()
    }

    fun throwDie() {
        dieViewModel.setCurrentRoll(Random.nextInt(dieSides)+1)
    }
    companion object{
        fun newInstance (sides: Int = 6) = DieFragment().apply {
            arguments = Bundle().apply {
                putInt(DIESIDE, sides)
            }
        }
    }
}