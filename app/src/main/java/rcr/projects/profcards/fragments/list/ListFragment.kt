package rcr.projects.profcards.fragments.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import rcr.projects.profcards.R
import rcr.projects.profcards.viewmodel.CartaoViewModel
import rcr.projects.profcards.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var mCartaoViewModel: CartaoViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater)

        val recyclerView = binding.recyclerview
        val adapter = ListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mCartaoViewModel = ViewModelProvider(this).get(CartaoViewModel::class.java)
        mCartaoViewModel.readAllData.observe(viewLifecycleOwner, Observer { cartao ->
            adapter.setData(cartao)
        })

        binding.btAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAll() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Sim") { _, _ ->
            mCartaoViewModel.deleteAll()
            Toast.makeText(requireContext(), "Excluir tudo", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){ _, _ ->

        }

        builder.setTitle("Excluir tudo?")
        builder.setMessage("Tem certeza que deseja excluir todos os cartões?")
        builder.create().show()
    }
}