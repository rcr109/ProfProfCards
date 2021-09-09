package rcr.projects.profcards.fragments.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import rcr.projects.profcards.R
import rcr.projects.profcards.databinding.FragmentListBinding
import rcr.projects.profcards.databinding.FragmentUpdateBinding
import rcr.projects.profcards.model.Cartao
import rcr.projects.profcards.viewmodel.CartaoViewModel

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private lateinit var mCartaoViewModel: CartaoViewModel
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater)
        binding.etuTitulo.setText(args.currentCartao.titulo)
        binding.etuDescricao.setText(args.currentCartao.mensagem)
        binding.etuData.setText(args.currentCartao.data)
        binding.etuStatus.setText(args.currentCartao.status)
        binding.etuDono.setText(args.currentCartao.dono)
        binding.btuCheck.setOnClickListener {
            updateItem()
        }
        mCartaoViewModel = ViewModelProvider(this).get(CartaoViewModel::class.java)

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem() {
        val titulo = binding.etuTitulo.text.toString()
        val mensagem = binding.etuDescricao.text.toString()
        val data = binding.etuData.text.toString()
        val status = binding.etuStatus.text.toString()
        val dono = binding.etuDono.text.toString()
        if (checkData(titulo, mensagem, data, status, dono)){
            val cartaoNovo = Cartao(args.currentCartao.id, titulo, mensagem, data, status, dono)
            mCartaoViewModel.updateCartao(cartaoNovo)
            Toast.makeText(requireContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkData(titulo : String, descricao : String, data : String, status : String, dono : String): Boolean {
        val verifica = (!titulo.isEmpty() && !descricao.isEmpty() && !data.isEmpty() and !status.isEmpty() and !dono.isEmpty())
        return verifica
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteCartao()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteCartao() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Sim") { _, _ ->
            mCartaoViewModel.deleteCartao(args.currentCartao)
            Toast.makeText(requireContext(), "${args.currentCartao.titulo} excluído", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Não"){ _, _ ->

        }

        builder.setTitle("Excluir ${args.currentCartao.titulo}?")
        builder.setMessage("Tem certeza que deseja excluir ${args.currentCartao.titulo}?")
        builder.create().show()
    }

}