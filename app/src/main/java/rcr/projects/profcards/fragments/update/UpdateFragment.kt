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
        binding.etuNome.setText(args.currentCartao.nome)
        binding.etuEmpresa.setText(args.currentCartao.empresa)
        binding.etuProfissao.setText(args.currentCartao.profissao)
        binding.etuTelefone.setText(args.currentCartao.telefone)
        binding.etuEmail.setText(args.currentCartao.email)
        binding.btuCheck.setOnClickListener {
            updateItem()
        }
        mCartaoViewModel = ViewModelProvider(this).get(CartaoViewModel::class.java)

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateItem() {
        val nome = binding.etuNome.text.toString()
        val empresa = binding.etuEmpresa.text.toString()
        val profissao = binding.etuProfissao.text.toString()
        val telefone = binding.etuTelefone.text.toString()
        val email = binding.etuEmail.text.toString()
        if (checkData(nome, empresa, profissao, telefone, email)){
            val cartaoNovo = Cartao(args.currentCartao.id, nome, empresa, profissao, telefone, email)
            mCartaoViewModel.updateCartao(cartaoNovo)
            Toast.makeText(requireContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkData(nome : String, empresa: String, profissao : String, telefone : String, email : String): Boolean {
        val verifica = (!nome.isEmpty() && !empresa.isEmpty() && !profissao.isEmpty() and !telefone.isEmpty() and !email.isEmpty())
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
            Toast.makeText(requireContext(), "${args.currentCartao.nome} excluído", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Não"){ _, _ ->

        }

        builder.setTitle("Excluir ${args.currentCartao.nome}?")
        builder.setMessage("Tem certeza que deseja excluir ${args.currentCartao.nome}?")
        builder.create().show()
    }

}