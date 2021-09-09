package rcr.projects.profcards.fragments.initial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.fragment.findNavController
import rcr.projects.profcards.R

import rcr.projects.profcards.databinding.FragmentInitialBinding
import rcr.projects.profcards.ui.MainActivity


class InitialFragment : Fragment() {
    private lateinit var binding: FragmentInitialBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentInitialBinding.inflate(inflater)

        binding.btCartoes.setOnClickListener {
            findNavController().navigate(R.id.action_initialFragment_to_listFragment)
        }

        binding.btSair.setOnClickListener {
            System.exit(0)
        }



        return binding.root
    }


}