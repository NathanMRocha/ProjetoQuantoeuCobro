package com.example.minhareceita

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var nomeReceitaInput: EditText
    private lateinit var nomeIngredienteInput: EditText
    private lateinit var quantidadeInput: EditText
    private lateinit var precoInput: EditText
    private lateinit var custoEmbalagensInput: EditText
    private lateinit var quantidadeUnidadesInput: EditText
    private lateinit var percentualLucroInput: EditText
    private lateinit var resultadoTextView: TextView
    private val receita = Receita("") // Inicializa a receita

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa os componentes da interface
        nomeReceitaInput = findViewById(R.id.nomeReceitaInput)
        nomeIngredienteInput = findViewById(R.id.nomeIngredienteInput)
        quantidadeInput = findViewById(R.id.quantidadeInput)
        precoInput = findViewById(R.id.precoInput)
        custoEmbalagensInput = findViewById(R.id.custoEmbalagensInput)
        quantidadeUnidadesInput = findViewById(R.id.quantidadeUnidadesInput)
        percentualLucroInput = findViewById(R.id.percentualLucroInput)
        resultadoTextView = findViewById(R.id.resultadoTextView)

        // Botão para adicionar ingredientes
        findViewById<Button>(R.id.adicionarIngredienteButton).setOnClickListener {
            adicionarIngrediente()
        }

        // Botão para calcular resultados
        findViewById<Button>(R.id.calcularButton).setOnClickListener {
            calcularResultados()
        }

        // Botão para limpar todos os ingredientes
        findViewById<Button>(R.id.limparButton).setOnClickListener {
            limparIngredientes()
        }
    }

    private fun adicionarIngrediente() {
        val nomeIngrediente = nomeIngredienteInput.text.toString()
        val quantidade = quantidadeInput.text.toString().toDoubleOrNull()
        val preco = precoInput.text.toString().toDoubleOrNull()

        if (nomeIngrediente.isEmpty() || quantidade == null || preco == null) {
            Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            return
        }

        receita.adicionarIngrediente(Ingrediente(nomeIngrediente, quantidade, preco))
        Toast.makeText(this, "Ingrediente adicionado!", Toast.LENGTH_SHORT).show()

        // Limpa os campos de ingrediente após adicionar
        nomeIngredienteInput.text.clear()
        quantidadeInput.text.clear()
        precoInput.text.clear()
    }

    private fun calcularResultados() {
        val custoEmbalagens = custoEmbalagensInput.text.toString().toDoubleOrNull()
        val quantidadeUnidades = quantidadeUnidadesInput.text.toString().toIntOrNull()
        val percentualLucro = percentualLucroInput.text.toString().toDoubleOrNull()

        if (custoEmbalagens == null || quantidadeUnidades == null || percentualLucro == null) {
            Toast.makeText(this, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            return
        }

        receita.definirCustoEmbalagens(custoEmbalagens)
        receita.definirQuantidadeUnidades(quantidadeUnidades)

        val custoTotal = receita.custoTotal()
        val precoVendaTotal = receita.calcularPrecoVenda(percentualLucro)
        val precoVendaPorUnidade = receita.calcularPrecoVendaPorUnidade(percentualLucro)

        resultadoTextView.text = """
            Custo total da receita: R$ ${"%.2f".format(custoTotal)}
            Preço de venda total sugerido: R$ ${"%.2f".format(precoVendaTotal)}
            Preço de venda por unidade sugerido: R$ ${"%.2f".format(precoVendaPorUnidade)}
        """.trimIndent()
    }

    private fun limparIngredientes() {
        receita.limparIngredientes()
        nomeReceitaInput.text.clear()
        custoEmbalagensInput.text.clear()
        quantidadeUnidadesInput.text.clear()
        percentualLucroInput.text.clear()
        resultadoTextView.text = ""
        Toast.makeText(this, "Ingredientes limpos!", Toast.LENGTH_SHORT).show()
    }
}
